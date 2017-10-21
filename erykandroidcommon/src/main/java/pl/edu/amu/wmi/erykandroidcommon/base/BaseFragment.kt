package pl.edu.amu.wmi.erykandroidcommon.base

import android.app.Fragment
import android.content.Context
import android.support.annotation.StringRes

import io.reactivex.CompletableTransformer
import io.reactivex.MaybeTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.SingleTransformer
import pl.edu.amu.wmi.erykandroidcommon.exception.AdapterLackException

/**
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@softra.pl> on 18.10.17.
 */
open class BaseFragment : Fragment() {

    private var baseAdapter: BaseAdapter? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            baseAdapter = context as BaseAdapter
        } catch (e: ClassCastException) {
            throw AdapterLackException(context, BaseAdapter::class.java)
        }

    }

    fun <T> singleWithThrobber(): SingleTransformer<T, T> {
        return { single ->
            single.doOnSubscribe(
                    { disposable -> baseAdapter!!.showThrobber(null) })
                    .doFinally({ baseAdapter!!.hideThrobber() })
        }
    }

    fun <T> singleWithThrobber(@StringRes throbberMessageResource: Int,
                               vararg formatArgs: Any): SingleTransformer<T, T> {
        return { single ->
            single.doOnSubscribe(
                    { disposable ->
                        baseAdapter!!.showThrobber(throbberMessageResource,
                                *formatArgs)
                    }).doFinally({ baseAdapter!!.hideThrobber() })
        }
    }

    fun <T> observableWithThrobber(
            @StringRes throbberMessageResource: Int, vararg formatArgs: Any): ObservableTransformer<T, T> {
        return { observable ->
            observable.doOnSubscribe(
                    { disposable ->
                        baseAdapter!!.showThrobber(throbberMessageResource,
                                *formatArgs)
                    }).doFinally({ baseAdapter!!.hideThrobber() })
        }
    }

    fun <T> maybeWithThrobber(@StringRes throbberMessageResource: Int,
                              vararg formatArgs: Any): MaybeTransformer<T, T> {
        return { maybe ->
            maybe.doOnSubscribe(
                    { disposable ->
                        baseAdapter!!.showThrobber(throbberMessageResource,
                                *formatArgs)
                    }).doFinally({ baseAdapter!!.hideThrobber() })
        }
    }

    fun completableWithThrobber(@StringRes throbberMessageResource: Int,
                                vararg formatArgs: Any): CompletableTransformer {
        return { completable ->
            completable.doOnSubscribe(
                    { disposable ->
                        baseAdapter!!.showThrobber(throbberMessageResource,
                                *formatArgs)
                    }).doFinally({ baseAdapter!!.hideThrobber() })
        }
    }
}
