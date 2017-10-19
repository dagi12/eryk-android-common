package pl.edu.amu.wmi.erykandroidcommon.di;

import android.app.Application;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.edu.amu.wmi.erykandroidcommon.service.UserService;

@Module
public class TestModule {

    private final Application mApplication;

    public TestModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    protected UserService provideUserService() {
        return Mockito.mock(UserService.class);
    }

}


