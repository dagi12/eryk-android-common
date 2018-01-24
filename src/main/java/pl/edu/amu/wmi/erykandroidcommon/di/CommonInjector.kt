package pl.edu.amu.wmi.erykandroidcommon.di

import android.app.Application

/**
 * @author Eryk Mariankowski <eryk.mariankowski@247.codes> on 24.01.18.
 */
open class CommonInjector {

    var _commonGraph: CommonApplicationComponent? = null

    public val commonGraph: CommonApplicationComponent
        get() = _commonGraph!!

    open fun initGraph(customApplication: Application) {
        _commonGraph = DaggerCommonApplicationComponent
            .builder()
            .commonApplicationModule(CommonApplicationModule(customApplication))
            .build()
    }


}