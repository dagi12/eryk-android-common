package pl.edu.amu.wmi.erykandroidcommon.di

import android.app.Application

/**
 * Stworzone przez Eryk Mariankowski dnia 25.01.18.
 */

interface AbstractInjector<T, in S : Application> {

    var _graph: T?

    val graph: T

    fun initGraph(application: S)
}
