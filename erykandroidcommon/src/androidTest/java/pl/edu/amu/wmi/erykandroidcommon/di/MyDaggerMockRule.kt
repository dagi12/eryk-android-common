package pl.edu.amu.wmi.erykandroidcommon.di

import android.app.Application
import android.support.test.InstrumentationRegistry

import it.cosenonjaviste.daggermock.DaggerMockRule

class MyDaggerMockRule : DaggerMockRule<TestComponent>(TestComponent::class.java, TestModule(app)) {
    
    init {
        set {
            //                getApp().setComponent(component);
        }
    }

    companion object {
        private val app: Application
            get() = InstrumentationRegistry.getInstrumentation()
                    .targetContext.applicationContext as Application
    }

}
