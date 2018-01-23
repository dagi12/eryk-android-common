package pl.edu.amu.wmi.erykandroidcommon.di

import android.app.Application
import pl.edu.amu.wmi.erykandroidcommon.user.UserStore
import retrofit2.Retrofit

interface CommonApplicationAdapter<out T : UserStore<*>> {

    fun provideUserStore(retrofit: Retrofit, application: Application): T
}