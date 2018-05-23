package pl.edu.amu.wmi.erykandroidcommon.rx

import io.reactivex.CompletableTransformer
import io.reactivex.MaybeTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Goodies and cookies for using reactive streams.
 *
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@softra.pl> on 09.06.2017
 */
object ObservableUtils {

    fun <T> bgObservableSchedulers(): ObservableTransformer<T, T> =
            ObservableTransformer { observable ->
                observable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
            }

    fun <T> bgSingleSchedulers() = SingleTransformer<T, T> {
        it
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun <T> bgMaybeSchedulers(): MaybeTransformer<T, T> =
            MaybeTransformer { maybe ->
                maybe
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
            }

    fun bgCompletableSchedulers() = CompletableTransformer { it.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()) }
}
