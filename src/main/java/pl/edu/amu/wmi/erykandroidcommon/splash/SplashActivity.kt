package pl.edu.amu.wmi.erykandroidcommon.splash

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import pl.edu.amu.wmi.erykandroidcommon.di.CommonApplication
import pl.edu.amu.wmi.erykandroidcommon.user.UserInterface
import pl.edu.amu.wmi.erykandroidcommon.user.UserStore
import javax.inject.Inject

/**
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@247.codes> on 20.10.17.
 */

abstract class SplashActivity<T : UserInterface> : AppCompatActivity() {

    @Inject
    lateinit var commonApplication: CommonApplication

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        userStore.user = user
        val activity = getStartActivity()
        startActivity(Intent(this, activity))
        finish()
    }

    abstract fun init()

    abstract val userStore: UserStore<T>

    abstract val user: T?

    private fun getStartActivity(): Class<*>? =
        if (userStore.isSigned()) commonApplication.mainActivity else commonApplication.register

}
