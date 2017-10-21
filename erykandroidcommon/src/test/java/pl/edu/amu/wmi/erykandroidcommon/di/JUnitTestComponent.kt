package pl.edu.amu.wmi.erykandroidcommon.di

import javax.inject.Singleton

import dagger.Component

@Singleton
@Component(modules = arrayOf(JUnitTestModule::class))
interface JUnitTestComponent {
    fun inject(abstractDaggerServiceTest: AbstractDaggerServiceTest)
}
