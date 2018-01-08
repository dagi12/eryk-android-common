package pl.edu.amu.wmi.erykandroidcommon.di

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pl.edu.amu.wmi.erykandroidcommon.BuildConfig
import pl.edu.amu.wmi.erykandroidcommon.location.LocationService
import pl.edu.amu.wmi.erykandroidcommon.service.PicassoCache
import pl.edu.amu.wmi.erykandroidcommon.service.UserService
import pl.edu.amu.wmi.erykandroidcommon.user.UserStore
import javax.inject.Singleton

@Module
class CommonApplicationModule(private val application: CommonApplication) {

    @Provides
    @Singleton
    fun provideContext(): Context = application

    @Provides
    @Singleton
    fun provideApplication(): CommonApplication = application

    @Provides
    @Singleton
    fun provideSharedPreferences(): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(application)

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
    fun provideUserStore(): UserStore = UserStore()

    @Provides
    @Singleton
    fun provideOkHttpClient(userService: UserService<*, *>): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.addInterceptor { chain ->
            var request = chain.request()
            if (userService.user != null) {
                request = request
                    .newBuilder().addHeader("token", userService.user!!.token).build()
            }
            chain.proceed(request)
        }
        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(interceptor).build()
        }
        return clientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideCachedImageManager(): PicassoCache = PicassoCache(application)

    @Provides
    @Singleton
    fun provideLocationService(): LocationService = LocationService(application)
}
