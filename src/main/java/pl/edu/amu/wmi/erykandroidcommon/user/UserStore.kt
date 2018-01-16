package pl.edu.amu.wmi.erykandroidcommon.user

import android.content.Context
import android.content.Intent
import pl.edu.amu.wmi.erykandroidcommon.di.CommonApplication
import javax.inject.Inject

/**
 * Stworzone przez Eryk Mariankowski dnia 12.01.2018.
 */
abstract class UserStore<T : UserInterface>(private val application: CommonApplication) {

    @Inject
    lateinit var context: Context

    abstract fun isSigned(): Boolean

    abstract var user: T?

    fun hardLogout() {
        val signOutIntent = Intent(context, application.register)
        signOutIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(signOutIntent)
    }

    abstract fun initUser(user: UserInterface?)

}