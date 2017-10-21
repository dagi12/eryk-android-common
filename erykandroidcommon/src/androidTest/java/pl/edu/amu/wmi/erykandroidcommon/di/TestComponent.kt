package pl.edu.amu.wmi.erykandroidcommon.di

import javax.inject.Singleton

import dagger.Component

@Singleton
@Component(modules = arrayOf(TestModule::class))
interface TestComponent
