package pl.edu.amu.wmi.erykandroidcommon.user

import android.content.Intent
import pl.edu.amu.wmi.erykandroidcommon.di.CommonApplication
import pl.edu.amu.wmi.erykandroidcommon.di.CommonApplication.Companion.context

/**
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@247.codes> on 19.11.17.
 */
const val IS_SIGNED = "IS_SIGNED"

class SignedStore(private val commonApplication: CommonApplication) {

    private var _isSigned = false

    var isSigned: Boolean
        get() = _isSigned
        set(value) {
            this._isSigned
            CommonApplication.preferences.getBoolean(IS_SIGNED).set(value)
        }

    init {
        _isSigned = CommonApplication.preferences.getBoolean(IS_SIGNED).get()
    }

    fun hardLogout() {
        val signOutIntent = Intent(context, commonApplication.register)
        signOutIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(signOutIntent)
    }


}
