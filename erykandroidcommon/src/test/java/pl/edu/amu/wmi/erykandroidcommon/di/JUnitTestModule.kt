package pl.edu.amu.wmi.erykandroidcommon.di

import android.content.Context
import com.google.gson.*
import com.google.gson.annotations.Expose
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import pl.edu.amu.wmi.erykandroidcommon.service.UserService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class JUnitTestModule(private val context: Context) {

    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        return GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).addSerializationExclusionStrategy(object : ExclusionStrategy {
            override fun shouldSkipField(fieldAttributes: FieldAttributes): Boolean {
                val expose = fieldAttributes.getAnnotation(Expose::class.java)
                return expose != null && !expose.serialize
            }

            override fun shouldSkipClass(aClass: Class<*>): Boolean = false
        })
                .addDeserializationExclusionStrategy(object : ExclusionStrategy {
                    override fun shouldSkipField(fieldAttributes: FieldAttributes): Boolean {
                        val expose = fieldAttributes.getAnnotation(Expose::class.java)
                        return expose != null && !expose.deserialize
                    }

                    override fun shouldSkipClass(aClass: Class<*>): Boolean = false
                })
                .create()
    }

//    @Provides
//    @Singleton
//    protected fun provideUserService(): UserService<*, *> {
//        return UserService(context, null!!)
//    }

    @Provides
    @Singleton
    protected fun provideOkHttpClient(UserService: UserService<*, *>): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor { chain ->
            var request = chain.request()
            if (UserService.user != null) {
                request = request.newBuilder().addHeader("token", UserService.user!!.token).build()
            }
            chain.proceed(request)
        }.build()
    }


    @Provides
    @Singleton
    internal fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(API_URL)
                .build()
    }

    companion object {

        private val API_URL = "http://reval.usermd.net/"
    }

}
