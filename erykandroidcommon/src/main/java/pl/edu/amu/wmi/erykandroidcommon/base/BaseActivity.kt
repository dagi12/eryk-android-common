package pl.edu.amu.wmi.erykandroidcommon.base

import android.annotation.TargetApi
import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.support.annotation.ColorRes
import android.support.v4.content.ContextCompat

import pl.edu.amu.wmi.erykandroidcommon.di.CommonApplication
import pl.edu.amu.wmi.erykandroidcommon.di.CommonApplicationComponent

/**
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@softra.pl> on 18.10.17.
 */
abstract class BaseActivity : Activity() {

    internal val commonComponent: CommonApplicationComponent?
        get() {
            val commonApplication = application as CommonApplication
            return commonApplication.commonApplicationComponent()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        changeNotificationBarColor(this)
    }

    @ColorRes
    abstract fun notificationColor(): Int

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    internal fun changeNotificationBarColor(activity: Activity) {
        if (Build.VERSION.SDK_INT >= 21) {
            activity
                    .window.statusBarColor = ContextCompat.getColor(activity, notificationColor())
        }
    }


}
