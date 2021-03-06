package pl.edu.amu.wmi.erykandroidcommon.di

import android.app.Application
import android.content.Context
import com.f2prateek.rx.preferences2.RxSharedPreferences
import com.google.gson.Gson
import dagger.Component
import pl.edu.amu.wmi.erykandroidcommon.base.BaseActivity
import pl.edu.amu.wmi.erykandroidcommon.service.PicassoCache
import pl.edu.amu.wmi.erykandroidcommon.verify.FormVerificationManager
import javax.inject.Singleton

/**
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@247.codes> on 21.10.17.
 */
@Singleton
@Component(modules = [CommonApplicationModule::class])
interface CommonApplicationComponent {

    fun provideContext(): Context
    fun picassoCache(): PicassoCache
    fun gson(): Gson
    fun application(): Application
    fun commonApplication(): CommonApplication
    fun provideSharedPreferences(): RxSharedPreferences
    fun inject(baseActivity: BaseActivity)
    fun inject(baseActivity: FormVerificationManager)
}
