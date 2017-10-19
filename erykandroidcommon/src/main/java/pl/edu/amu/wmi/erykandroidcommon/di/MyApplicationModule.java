package pl.edu.amu.wmi.erykandroidcommon.di;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import pl.edu.amu.wmi.erykandroidcommon.BuildConfig;
import pl.edu.amu.wmi.erykandroidcommon.location.LocationService;
import pl.edu.amu.wmi.erykandroidcommon.service.PicassoCache;
import pl.edu.amu.wmi.erykandroidcommon.service.UserService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class MyApplicationModule {

    private final Application application;

    private final Class mainActivity;

    private final String apiUrl;


    public MyApplicationModule(Application application, Class mainActivity, String apiUrl) {
        this.application = application;
        this.mainActivity = mainActivity;
        this.apiUrl = apiUrl;

    }

    @Provides
    @Singleton
    protected SharedPreferences provideSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    @Singleton
    protected Application provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    protected Gson provideGson() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).addSerializationExclusionStrategy(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes fieldAttributes) {
                        final Expose expose = fieldAttributes.getAnnotation(Expose.class);
                        return expose != null && !expose.serialize();
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> aClass) {
                        return false;
                    }
                })
                .addDeserializationExclusionStrategy(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes fieldAttributes) {
                        final Expose expose = fieldAttributes.getAnnotation(Expose.class);
                        return expose != null && !expose.deserialize();
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> aClass) {
                        return false;
                    }
                })
                .create();
    }

    @Provides
    @Singleton
    protected UserService provideUserService() {
        return new UserService(application, mainActivity);
    }

    @Provides
    @Singleton
    protected OkHttpClient provideOkHttpClient(final UserService UserService) {
        final OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (UserService.user() != null) {
                    request = request.newBuilder().addHeader("token", UserService.user().getToken()).build();
                }
                return chain.proceed(request);
            }
        });
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            clientBuilder.addInterceptor(interceptor).build();
        }
        return clientBuilder.build();
    }

    @Provides
    @Singleton
    protected Retrofit provideRetrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit
                .Builder()
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(apiUrl)
                .build();
    }

    @Provides
    @Singleton
    protected PicassoCache provideCachedImageManager() {
        return new PicassoCache(application);
    }

    @Provides
    @Singleton
    protected LocationService provideLocationService() {
        return new LocationService(application);
    }

}
