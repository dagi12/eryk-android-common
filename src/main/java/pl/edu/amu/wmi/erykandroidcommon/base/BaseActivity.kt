package pl.edu.amu.wmi.erykandroidcommon.base

import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.TextView
import io.reactivex.CompletableTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import pl.edu.amu.wmi.erykandroidcommon.R
import pl.edu.amu.wmi.erykandroidcommon.di.CommonApplication
import pl.edu.amu.wmi.erykandroidcommon.di.CommonApplicationComponent
import pl.edu.amu.wmi.erykandroidcommon.di.CommonInjector
import pl.edu.amu.wmi.erykandroidcommon.rx.ObservableUtils.bgCompletableSchedulers
import pl.edu.amu.wmi.erykandroidcommon.rx.ObservableUtils.bgObservableSchedulers
import pl.edu.amu.wmi.erykandroidcommon.rx.ObservableUtils.bgSingleSchedulers
import pl.edu.amu.wmi.erykandroidcommon.ui.progress.ButteryProgressBar

/**
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@softra.pl> on 18.10.17.
 */
abstract class BaseActivity : AppCompatActivity(), BaseAdapter {

    private var progressBar: ButteryProgressBar? = null

    override fun showThrobber() {
        if (progressBar == null) {
            initIndeterminateProgress()
        }
        progressBar!!.visibility = VISIBLE
    }

    override fun hideThrobber() {
        progressBar?.visibility = INVISIBLE
    }

    override fun <T> singleWithThrobber() = SingleTransformer<T, T> {
        it
                .compose(bgSingleSchedulers())
                .doOnSubscribe { showThrobber() }
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally({ hideThrobber() })
    }

    override fun <T> observableWithThrobber() = ObservableTransformer<T, T> {
        it
                .compose(bgObservableSchedulers())
                .doOnSubscribe { showThrobber() }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(::hideThrobber)
    }

    override fun completableWithThrobber() = CompletableTransformer {
        it
                .compose(bgCompletableSchedulers())
                .doOnSubscribe { showThrobber() }
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally({ hideThrobber() })
    }

    private val progressColor: Int by lazy {
        val app: CommonApplication = applicationContext as CommonApplication
        app.appConfig.progressColor
    }

    private fun initIndeterminateProgress() {
        val bar = ButteryProgressBar(this, null, progressColor)
//        findViewById<ViewGroup>(android.R.id.content).getChildAt(0) as ViewGroup
//        (window.decorView as ViewGroup).addView(bar)
        findViewById<ViewGroup>(android.R.id.content).addView(bar)
        progressBar = bar
    }

    private val snack: Snackbar by lazy {
        val snack = Snackbar.make(findViewById(android.R.id.content), getString(pl.edu.amu.wmi.erykandroidcommon.R.string.no_internet), Snackbar.LENGTH_INDEFINITE)
        val errColor = ContextCompat.getColor(this, android.R.color.holo_red_dark)
        val snackView = snack.view
        val textView = snackView.findViewById(android.support.design.R.id.snackbar_text) as TextView
        textView.setCompoundDrawablesRelativeWithIntrinsicBounds(android.R.drawable.ic_dialog_alert, 0, 0, 0)
        textView.compoundDrawablePadding = resources.getDimensionPixelOffset(R.dimen.padding_1x)
        textView.setTextColor(ContextCompat.getColor(this, android.R.color.white))
        snackView.setBackgroundColor(errColor)
        snack
    }

    override fun handleNoInternet() {
        snack.show()
    }

    override fun handleInternet() {
        snack.dismiss()
    }

    val commonGraph: CommonApplicationComponent
        get() = CommonInjector.graph
}
