package pl.edu.amu.wmi.erykandroidcommon.base

import io.reactivex.CompletableTransformer
import io.reactivex.MaybeTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.SingleTransformer

/**
 * Base interface for [android.support.v4.app.Fragment] to [android.app.Activity]
 * communication.
 *
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@softra.pl> on 24.03.2017.
 */
interface BaseAdapter {

    fun showThrobber()

    fun hideThrobber()

    fun handleNoInternet()

    fun handleInternet()

    fun <T> singleWithThrobber(): SingleTransformer<T, T>

    fun completableWithThrobber(): CompletableTransformer

    fun <T> observableWithThrobber(): ObservableTransformer<T, T>

    fun <T> maybeWithThrobber(): MaybeTransformer<T, T>
}
