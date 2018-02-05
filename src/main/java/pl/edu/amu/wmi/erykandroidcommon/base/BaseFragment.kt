package pl.edu.amu.wmi.erykandroidcommon.base

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import io.reactivex.CompletableTransformer
import io.reactivex.MaybeTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.SingleTransformer
import pl.edu.amu.wmi.erykandroidcommon.exception.AdapterLackException
import timber.log.Timber
import java.net.ConnectException
import java.net.UnknownHostException

/**
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@softra.pl> on 18.10.17.
 */
open class BaseFragment : Fragment() {

    lateinit var baseAdapter: BaseAdapter

    lateinit var activity: Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.activity = getActivity()!!
        initBaseAdapter()
    }

    open fun initBaseAdapter() {
        baseAdapter = try {
            activity as BaseAdapter
        } catch (e: ClassCastException) {
            throw AdapterLackException(activity, BaseAdapter::class.java)
        }
    }

    fun handleInternet() {
        baseAdapter.handleInternet()
    }

    @SuppressLint("TimberExceptionLogging")
    fun handleNoInternet(throwable: Throwable, errorMsg: String) {
        if (throwable is UnknownHostException) {
            baseAdapter.handleNoInternet()
        } else {
            Timber.e(throwable, errorMsg)
        }
    }

    fun handleNoInternet(throwable: Throwable) {
        if (throwable is UnknownHostException || throwable is ConnectException) {
            baseAdapter.handleNoInternet()
        } else {
            Timber.e(throwable)
        }
    }

    fun <T> singleWithThrobber() = SingleTransformer<T, T> {
        it
            .doOnSubscribe { baseAdapter.showThrobber() }
            .doFinally({ baseAdapter.hideThrobber() })
    }

    fun <T> observableWithThrobber() = ObservableTransformer<T, T> {
        it
            .doOnSubscribe { _ -> baseAdapter.showThrobber() }
            .doFinally({ baseAdapter.hideThrobber() })
    }

    fun <T> maybeWithThrobber() =
        MaybeTransformer<T, T> {
            it
                .doOnSubscribe({ _ -> baseAdapter.showThrobber() })
                .doFinally({ baseAdapter.hideThrobber() })
        }

    fun completableWithThrobber() =
        CompletableTransformer {
            it
                .doOnSubscribe({ baseAdapter.showThrobber() })
                .doFinally({ baseAdapter.hideThrobber() })
        }
}
