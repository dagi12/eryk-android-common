package pl.edu.amu.wmi.erykandroidcommon.di

import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.annotations.Expose

import java.io.IOException

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import pl.edu.amu.wmi.erykandroidcommon.service.UserService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class JUnitTestModule {


    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        return GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).addSerializationExclusionStrategy(object : ExclusionStrategy {
            override fun shouldSkipField(fieldAttributes: FieldAttributes): Boolean {
                val expose = fieldAttributes.getAnnotation(Expose::class.java)
                return expose != null && !expose.serialize()
            }

            override fun shouldSkipClass(aClass: Class<*>): Boolean {
                return false
            }
        })
                .addDeserializationExclusionStrategy(object : ExclusionStrategy {
                    override fun shouldSkipField(fieldAttributes: FieldAttributes): Boolean {
                        val expose = fieldAttributes.getAnnotation(Expose::class.java)
                        return expose != null && !expose.deserialize()
                    }

                    override fun shouldSkipClass(aClass: Class<*>): Boolean {
                        return false
                    }
                })
                .create()
    }

    @Provides
    @Singleton
    protected fun provideUserService(): UserService<*, *> {
        return UserService(null!!, null!!)
    }

    @Provides
    @Singleton
    protected fun provideOkHttpClient(UserService: UserService<*, *>): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor { chain ->
            var request = chain.request()
            if (UserService.user() != null) {
                request = request.newBuilder().addHeader("token", UserService.user().getToken()).build()
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
