package pl.edu.amu.wmi.erykandroidcommon.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(TestModule::class))
interface TestComponent
