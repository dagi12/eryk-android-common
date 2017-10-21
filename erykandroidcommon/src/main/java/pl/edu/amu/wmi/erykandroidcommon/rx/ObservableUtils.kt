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
internal object ObservableUtils {

    private val observableSchedulersTransformer = { observable ->
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    private val singleSchedulersTransformer = { single ->
        single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    private val maybeSchedulersTransformer = { maybe ->
        maybe.subscribeOn(
                Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    fun <T> backgroundObservableSchedulers(): ObservableTransformer<T, T> {
        return observableSchedulersTransformer as ObservableTransformer<T, T>
    }

    fun <T> backgroundSingleSchedulers(): SingleTransformer<T, T> {
        return singleSchedulersTransformer as SingleTransformer<T, T>
    }

    fun <T> backgroundMaybeSchedulers(): MaybeTransformer<T, T> {
        return maybeSchedulersTransformer as MaybeTransformer<T, T>
    }

    fun backgroundCompletableSchedulers(): CompletableTransformer {
        return { completable ->
            completable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }
}
