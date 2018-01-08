package pl.edu.amu.wmi.erykandroidcommon.base

/**
 * Base interface for [android.support.v4.app.Fragment] to [android.app.Activity]
 * communication.
 *
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@softra.pl> on 24.03.2017.
 */
interface BaseAdapter {

    fun showThrobber()

    fun hideThrobber()
}
