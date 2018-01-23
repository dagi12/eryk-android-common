package pl.edu.amu.wmi.erykandroidcommon.di

import android.app.Activity
import android.preference.PreferenceManager
import android.support.multidex.MultiDexApplication
import android.util.Log
import com.crashlytics.android.Crashlytics
import com.f2prateek.rx.preferences2.RxSharedPreferences
import com.jakewharton.threetenabp.AndroidThreeTen
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import io.fabric.sdk.android.Fabric
import io.reactivex.plugins.RxJavaPlugins
import net.ypresto.timbertreeutils.CrashlyticsLogExceptionTree
import net.ypresto.timbertreeutils.CrashlyticsLogTree
import timber.log.Timber

/**
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@247.codes> on 21.10.17.
 */
abstract class CommonApplication<T : CommonInjector> : MultiDexApplication() {

    lateinit var commonGraph: CommonApplicationComponent

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        initLogging()
        RxJavaPlugins.setErrorHandler { throwable ->
            Timber.e(throwable, UNHANDLED_EXCEPTION_MESSAGE)
        }
        preferences = RxSharedPreferences.create(
            PreferenceManager.getDefaultSharedPreferences(applicationContext))
        val module = CommonApplicationModule(this)
        commonGraph = DaggerCommonApplicationComponent
            .builder()
            .commonApplicationModule(module)
            .build()
        preConfig()
    }

    abstract fun preConfig()

    private fun initLogging() {
        Fabric.with(this, Crashlytics())
        val formatStrategy = PrettyFormatStrategy.newBuilder()
            .methodOffset(7)
            .tag("")
            .build()
        Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))
        Timber.plant(object : Timber.DebugTree() {
            override fun log(priority: Int, tag: String?, message: String, t: Throwable?) =
                Logger.log(priority, tag, message, t)
        })
        Timber.plant(CrashlyticsLogTree(Log.DEBUG))
        Timber.plant(CrashlyticsLogExceptionTree())
    }

    companion object {
        @JvmStatic lateinit var preferences: RxSharedPreferences
        private val UNHANDLED_EXCEPTION_MESSAGE = "Unhandled exception"
    }

    abstract val appConstants: AppConstants

    abstract val isDebug: Boolean

    abstract val mainActivity: Class<out Activity>

    abstract val register: Class<out Activity>

    lateinit var injector: T
}
