package pl.edu.amu.wmi.erykandroidcommon.di

import android.app.Application
import dagger.Module

@Module(includes = [CommonApplicationModule::class])
class TestModule(private val mApplication: Application) {


}