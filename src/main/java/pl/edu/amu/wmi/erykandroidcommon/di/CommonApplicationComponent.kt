package pl.edu.amu.wmi.erykandroidcommon.di

import com.google.gson.Gson
import dagger.Component
import pl.edu.amu.wmi.erykandroidcommon.base.BaseActivity
import pl.edu.amu.wmi.erykandroidcommon.verify.FormVerificationManager
import javax.inject.Singleton

/**
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@247.codes> on 21.10.17.
 */
@Singleton
@Component(modules = [CommonApplicationModule::class])
interface CommonApplicationComponent {

    fun gson(): Gson
    fun application(): CommonApplication
    fun inject(baseActivity: BaseActivity)
    fun inject(baseActivity: FormVerificationManager)
}
