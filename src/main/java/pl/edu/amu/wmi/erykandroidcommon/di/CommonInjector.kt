package pl.edu.amu.wmi.erykandroidcommon.di

/**
 * Stworzone przez Eryk Mariankowski dnia 24.01.18.
 */

object CommonInjector : AbstractInjector<CommonApplicationComponent, CommonApplication> {

    override var _graph: CommonApplicationComponent? = null

    override val graph: CommonApplicationComponent by lazy { _graph!! }

    override fun initGraph(application: CommonApplication) {
        _graph = DaggerCommonApplicationComponent
            .builder()
            .commonApplicationModule(CommonApplicationModule(application))
            .build()
    }
}
