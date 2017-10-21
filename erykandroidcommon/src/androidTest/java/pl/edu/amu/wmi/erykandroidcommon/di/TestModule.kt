package pl.edu.amu.wmi.erykandroidcommon.di

import android.app.Application

import org.mockito.Mockito

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import pl.edu.amu.wmi.erykandroidcommon.service.UserService

@Module
class TestModule(private val mApplication: Application) {

    @Provides
    @Singleton
    fun provideUserService(): UserService<*, *> {
        return Mockito.mock(UserService<*, *>::class.java)
    }

}


