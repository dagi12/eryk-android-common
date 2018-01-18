package pl.edu.amu.wmi.erykandroidcommon.splash

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import pl.edu.amu.wmi.erykandroidcommon.di.CommonApplication
import pl.edu.amu.wmi.erykandroidcommon.user.UserInterface
import pl.edu.amu.wmi.erykandroidcommon.user.UserStore
import javax.inject.Inject

/**
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@247.codes> on 20.10.17.
 */

abstract class SplashActivity : Activity() {

    @Inject
    lateinit var commonApplication: CommonApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        userStore.initUser(user)
        val activity = getStartActivity()
        startActivity(Intent(this, activity))
        finish()
    }

    abstract fun init()

    abstract val userStore: UserStore<out UserInterface>

    abstract val user: UserInterface?

    private fun getStartActivity(): Class<*>? =
        if (userStore.isSigned()) (applicationContext as CommonApplication).mainActivity else commonApplication.register

}
