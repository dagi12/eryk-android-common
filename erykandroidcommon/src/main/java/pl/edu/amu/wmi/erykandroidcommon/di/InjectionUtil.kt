package pl.edu.amu.wmi.erykandroidcommon.di

import android.content.Context

/**
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@247.codes> on 21.10.17.
 */
class InjectionUtil {

    internal fun getCommonComponent(context: Context): CommonApplicationComponent? {
        return (context.applicationContext as CommonApplication)
                .commonApplicationComponent()
    }

}
