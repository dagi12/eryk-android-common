package pl.edu.amu.wmi.erykandroidcommon.di

/**
 * Stworzone przez Eryk Mariankowski dnia 24.01.18.
 */

object CommonInjector : AbstractInjector<CommonApplicationComponent, CommonApplication> {

    override lateinit var graph: CommonApplicationComponent

    override fun initGraph(application: CommonApplication) {
        graph = DaggerCommonApplicationComponent
                .builder()
                .commonApplicationModule(CommonApplicationModule(application))
                .build()
    }
}
