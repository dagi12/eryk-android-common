package pl.edu.amu.wmi.erykandroidcommon.di

import android.app.Application
import dagger.Module
import dagger.Provides
import org.mockito.Mockito
import pl.edu.amu.wmi.erykandroidcommon.service.UserService
import javax.inject.Singleton

@Module
class TestModule(private val mApplication: Application) {

    @Provides
    @Singleton
    fun provideUserService(): UserService<*, *> {
        val userServiceClass: Class<UserService<*, *>> = UserService::class.java
        return Mockito.mock(userServiceClass)
    }

}


