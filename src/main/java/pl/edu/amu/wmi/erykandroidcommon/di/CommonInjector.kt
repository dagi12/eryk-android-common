package pl.edu.amu.wmi.erykandroidcommon.di

/**
 * Stworzone przez Eryk Mariankowski dnia 24.01.18.
 */

object CommonInjector {

    var _commonGraph: CommonApplicationComponent? = null

    val commonGraph: CommonApplicationComponent by lazy { _commonGraph!! }

    fun initGraph(application: CommonApplication) {
        _commonGraph = DaggerCommonApplicationComponent
            .builder()
            .commonApplicationModule(CommonApplicationModule(application))
            .build()
    }
}
