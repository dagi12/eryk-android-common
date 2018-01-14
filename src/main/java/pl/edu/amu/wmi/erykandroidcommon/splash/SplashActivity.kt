package pl.edu.amu.wmi.erykandroidcommon.splash

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import pl.edu.amu.wmi.erykandroidcommon.di.CommonApplication
import pl.edu.amu.wmi.erykandroidcommon.user.SignedStore
import javax.inject.Inject

/**
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@247.codes> on 20.10.17.
 */

abstract class SplashActivity : Activity() {

    @Inject
    lateinit var signedStore: SignedStore

    @Inject
    lateinit var commonApplication: CommonApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activity = if (signedStore.isSigned) commonApplication.mainActivity else commonApplication.register
        startActivity(Intent(this, activity))
        finish()
    }
}
