package pl.edu.amu.wmi.erykandroidcommon.di

import android.app.Application

/**
 * Stworzone przez Eryk Mariankowski dnia 25.01.18.
 */

interface AbstractInjector<T : Any, in S : Application> {

    var graph: T

    fun initGraph(application: S)
}
