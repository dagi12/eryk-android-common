package pl.edu.amu.wmi.erykandroidcommon.di;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = JUnitTestModule.class)
public interface JUnitTestComponent {
    void inject(AbstractDaggerServiceTest abstractDaggerServiceTest);
}
