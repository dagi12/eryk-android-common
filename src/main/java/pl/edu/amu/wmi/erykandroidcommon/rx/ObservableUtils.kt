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

    fun <T> backgroundObservableSchedulers(): ObservableTransformer<T, T> =
        ObservableTransformer { observable ->
            observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }

    fun <T> backgroundSingleSchedulers(): SingleTransformer<T, T> =
        SingleTransformer { single ->
            single
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }

    fun <T> backgroundMaybeSchedulers(): MaybeTransformer<T, T> =
        MaybeTransformer { maybe ->
            maybe
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }

    fun backgroundCompletableSchedulers(): CompletableTransformer =
        CompletableTransformer { completable ->
            completable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
}
