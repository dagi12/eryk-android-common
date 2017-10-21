package pl.edu.amu.wmi.erykandroidcommon.di

import android.support.multidex.MultiDexApplication
import android.util.Log
import com.crashlytics.android.Crashlytics
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import io.fabric.sdk.android.Fabric
import io.reactivex.plugins.RxJavaPlugins
import net.ypresto.timbertreeutils.CrashlyticsLogExceptionTree
import net.ypresto.timbertreeutils.CrashlyticsLogTree
import timber.log.Timber

/**
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@247.codes> on 21.10.17.
 */
abstract class CommonApplication : MultiDexApplication() {

    var commonApplicationComponent: CommonApplicationComponent? = null

    override fun onCreate() {
        super.onCreate()
        initLogging()
        RxJavaPlugins.setErrorHandler { throwable -> Timber.e(throwable, UNHANDLED_EXCEPTION_MESSAGE) }
        commonApplicationComponent = DaggerCommonApplicationComponent
                .builder()
                .commonApplicationModule(CommonApplicationModule(applicationContext))
                .build()
    }

    private fun initLogging() {
        Fabric.with(this, Crashlytics())
        Logger.addLogAdapter(AndroidLogAdapter())
        Timber.plant(object : Timber.DebugTree() {
            override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                Logger.log(priority, tag, message, t)
            }
        })
        Timber.plant(CrashlyticsLogTree(Log.DEBUG))
        Timber.plant(CrashlyticsLogExceptionTree())
    }

    companion object {

        private val UNHANDLED_EXCEPTION_MESSAGE = "Unhandled exception"
    }
}
