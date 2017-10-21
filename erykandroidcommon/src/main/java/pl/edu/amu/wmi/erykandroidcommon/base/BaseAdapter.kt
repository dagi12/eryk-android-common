package pl.edu.amu.wmi.erykandroidcommon.base

import android.content.Context
import android.support.annotation.StringRes

/**
 * Base interface for [android.support.v4.app.Fragment] to [android.app.Activity]
 * communication.
 *
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@softra.pl> on 24.03.2017.
 */
interface BaseAdapter {

    val context: Context

    fun showThrobber(@StringRes messageId: Int, vararg formatArgs: Any)

    fun showThrobber(message: String)

    fun hideThrobber()

    fun showKeyboard()

    fun hideKeyboard()

    fun goBack()
}
