package pl.edu.amu.wmi.erykandroidcommon.base

import android.app.Fragment
import android.os.Bundle
import io.reactivex.CompletableTransformer
import io.reactivex.MaybeTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.SingleTransformer
import pl.edu.amu.wmi.erykandroidcommon.exception.AdapterLackException

/**
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@softra.pl> on 18.10.17.
 */
open class BaseFragment : Fragment() {

    lateinit var baseAdapter: BaseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseAdapter = try {
            activity as BaseAdapter
        } catch (e: ClassCastException) {
            throw AdapterLackException(activity, BaseAdapter::class.java)
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
