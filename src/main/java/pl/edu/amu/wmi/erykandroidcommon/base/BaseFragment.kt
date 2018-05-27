package pl.edu.amu.wmi.erykandroidcommon.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import pl.edu.amu.wmi.erykandroidcommon.exception.AdapterLackException
import timber.log.Timber
import java.net.ConnectException
import java.net.UnknownHostException

/**
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@softra.pl> on 18.10.17.
 */
open class BaseFragment : Fragment() {

    private lateinit var baseAdapter: BaseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBaseAdapter()
    }

    open fun initBaseAdapter() {
        baseAdapter = try {
            activity as BaseAdapter
        } catch (e: ClassCastException) {
            throw AdapterLackException(activity!!, BaseAdapter::class.java)
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

    fun <T> singleWithThrobber() = baseAdapter.singleWithThrobber<T>()

    fun <T> observableWithThrobber() = baseAdapter.observableWithThrobber<T>()

    fun <T> maybeWithThrobber() = baseAdapter.maybeWithThrobber<T>()

    fun completableWithThrobber() = baseAdapter.completableWithThrobber()
}
