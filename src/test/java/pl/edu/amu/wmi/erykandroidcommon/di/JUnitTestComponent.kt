package pl.edu.amu.wmi.erykandroidcommon.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [JUnitTestModule::class])
interface JUnitTestComponent {
    fun inject(abstractDaggerServiceTest: AbstractDaggerServiceTest)
}
