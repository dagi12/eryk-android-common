package pl.edu.amu.wmi.erykandroidcommon.splash

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import pl.edu.amu.wmi.erykandroidcommon.di.CommonApplication
import pl.edu.amu.wmi.erykandroidcommon.user.UserStore
import javax.inject.Inject

/**
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@247.codes> on 20.10.17.
 */

abstract class SplashActivity<T : Activity, S : Activity> : Activity() {

    @Inject
    lateinit var userStore: UserStore

    @Inject
    lateinit var commonApplication: CommonApplication

    abstract val register: Class<S>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activity = if (userStore.isSigned) commonApplication.mainActivity else register
        startActivity(Intent(this, activity))
        finish()
    }
}
