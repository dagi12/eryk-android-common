package pl.edu.amu.wmi.erykandroidcommon.user

import android.content.Intent
import pl.edu.amu.wmi.erykandroidcommon.di.CommonApplication

/**
 * Stworzone przez Eryk Mariankowski dnia 12.01.2018.
 */
abstract class UserStore<T : UserInterface>(private val application: CommonApplication) {

    lateinit var commonApplication: CommonApplication

    abstract fun isSigned(): Boolean

    abstract var user: T?

    fun hardLogout() {
        val signOutIntent = Intent(application, commonApplication.register)
        signOutIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        application.startActivity(signOutIntent)
    }

    abstract fun initUser(user: UserInterface?)

}