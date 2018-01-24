package pl.edu.amu.wmi.erykandroidcommon.test

import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.powermock.api.mockito.PowerMockito
import org.powermock.api.mockito.PowerMockito.`when`
import org.powermock.core.classloader.annotations.PowerMockIgnore
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.rule.PowerMockRule
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import pl.edu.amu.wmi.erykandroidcommon.BuildConfig

/**
 * @author Eryk Mariankowski <eryk.mariankowski@247.codes> on 16.01.18.
 */
@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = [26])
@PowerMockIgnore("org.mockito.*", "org.robolectric.*", "android.*")
@PrepareForTest(TestStatic::class, TestNonStatic::class)
class PowerMockTest {

    @JvmField
    @Rule
    val rule = PowerMockRule()

    @Test
    fun testStaticMocking() {
        PowerMockito.mockStatic(TestStatic::class.java)
        `when`(TestStatic.testMsg()).thenReturn("hello mock")
        assertTrue(TestStatic.testMsg() == "hello mock")
    }

    @Test
    fun testMocking() {
        val mock = PowerMockito.mock(TestNonStatic::class.java)
        PowerMockito.`when`(mock.nonStaticMethod()).thenReturn("hello mock")
        assertTrue(mock.nonStaticMethod() == "hello mock")
    }

    @Test
    fun testSuppress() {
        PowerMockito.suppress(PowerMockito.method(TestNonStatic::class.java, "throwMethod"))
        TestNonStatic().throwMethod()
    }

}