package pl.edu.amu.wmi.erykandroidcommon.di

import android.app.Application
import android.content.Context
import android.preference.PreferenceManager
import com.f2prateek.rx.preferences2.RxSharedPreferences
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.ConnectionPool
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pl.edu.amu.wmi.erykandroidcommon.location.LocationService
import pl.edu.amu.wmi.erykandroidcommon.service.PicassoCache
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

fun retroFactory(baseUrl: String, gson: Gson, okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder().baseUrl(baseUrl)
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

fun okHttpFactory(debug: Boolean, interceptor: Interceptor?): OkHttpClient {
    val clientBuilder = OkHttpClient.Builder()
    interceptor?.let {
        clientBuilder.addInterceptor(interceptor)
    }
    val sslContext = SSLContext.getInstance("SSL")
    val connectionPool = ConnectionPool(5, 60, TimeUnit.SECONDS)
    val connectionTrustManager = arrayOf<TrustManager>(object : X509TrustManager {
        override fun checkServerTrusted(p0: Array<out X509Certificate>?, p1: String?) = Unit

        override fun checkClientTrusted(p0: Array<out X509Certificate>?, p1: String?) = Unit

        override fun getAcceptedIssuers(): Array<out X509Certificate> = arrayOf()
    })
    sslContext.init(null, connectionTrustManager, SecureRandom())
    clientBuilder.sslSocketFactory(sslContext.socketFactory)
    clientBuilder.hostnameVerifier { _, _ -> true }

    clientBuilder.connectionPool(connectionPool)
    if (debug) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        clientBuilder.addInterceptor(loggingInterceptor).build()
    }
    return clientBuilder.build()
}

@Module
class CommonApplicationModule(private val application: CommonApplication) {

    @Provides
    @Singleton
    fun provideContext(): Context = application

    @Provides
    @Singleton
    fun provideApplication(): Application = application

    @Provides
    @Singleton
    fun provideCommonApplication(): CommonApplication = application

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
//            .addSerializationExclusionStrategy(object : ExclusionStrategy {
//                override fun shouldSkipField(fieldAttributes: FieldAttributes): Boolean {
//                    val expose: Expose = fieldAttributes.getAnnotation(Expose::class.java)
//                    return !expose.serialize

//                }
//
//                override fun shouldSkipClass(aClass: Class<*>): Boolean = false
//            })
//            .addDeserializationExclusionStrategy(object : ExclusionStrategy {
//                override fun shouldSkipField(fieldAttributes: FieldAttributes): Boolean {
//                    val expose = fieldAttributes.getAnnotation(Expose::class.java)
//                    return expose != null && !expose.deserialize
//                }
//
//                override fun shouldSkipClass(aClass: Class<*>): Boolean = false
//            })
        .create()

    @Provides
    @Singleton
    fun provideCachedImageManager(): PicassoCache = PicassoCache(application)

    @Provides
    @Singleton
    fun provideLocationService(): LocationService = LocationService(application)

    @Provides
    @Singleton
    fun provideSharedPreferences(): RxSharedPreferences = RxSharedPreferences.create(
        PreferenceManager.getDefaultSharedPreferences(application))
}
