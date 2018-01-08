package pl.edu.amu.wmi.erykandroidcommon.di

abstract class AbstractDaggerServiceTest {

    protected fun setUp() {
        val component: JUnitTestComponent = DaggerJUnitTestComponent
                .builder()
                .jUnitTestModule(JUnitTestModule())
                .build()
        component.inject(this)
    }

    protected fun isEmpty(str: String?): Boolean = str == null || str.isEmpty()
}