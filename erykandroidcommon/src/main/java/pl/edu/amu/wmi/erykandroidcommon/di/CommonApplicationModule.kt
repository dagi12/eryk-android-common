package pl.edu.amu.wmi.erykandroidcommon.di;

import android.preference.PreferenceManager
import com.google.gson.FieldAttributes
import com.google.gson.FieldNamingPolicy
import com.google.gson.annotations.Expose
import dagger.Module
import dagger.Provides
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import pl.edu.amu.wmi.erykandroidcommon.BuildConfig
import javax.inject.Singleton

@Module
public class CommonApplicationModule {

    private final Context context;


    public CommonApplicationModule(Context context) {
        this.context = context;

    }

    @Provides
    @Singleton
    protected SharedPreferences provideSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(context);
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

//    @Provides
//    @Singleton
//    protected UserService provideUserService() {
//        return new UserService(context, mainActivity);
//    }

    @Provides
    @Singleton
    protected OkHttpClient provideOkHttpClient(final UserService userService) {
        final OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.addInterceptor(chain -> {
            Request request = chain.request();
            if (userService.user() != null) {
                request = request.newBuilder().addHeader("token", userService.user().getToken()).build();
            }
            return chain.proceed(request);
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
    protected PicassoCache provideCachedImageManager() {
        return new PicassoCache(context);
    }

    @Provides
    @Singleton
    protected LocationService provideLocationService() {
        return new LocationService(context);
    }

}
