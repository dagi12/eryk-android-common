package pl.edu.amu.wmi.erykandroidcommon.service

import android.app.Application
import android.content.Context
import android.content.Intent

import lombok.Getter
import lombok.Setter

/**
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@softra.pl> on 18.10.17.
 */
class UserService<T : Token, S>(application: Application, private val startActivity: Class<S>) {

    private val context: Context

    @Getter
    @Setter
    private var user: T? = null

    val isSignedIn: Boolean
        get() = user != null

    init {
        this.context = application
    }

    fun hardLogout() {
        this.user = null
        val signOutIntent = Intent(context, startActivity)
        signOutIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(signOutIntent)
    }

}
