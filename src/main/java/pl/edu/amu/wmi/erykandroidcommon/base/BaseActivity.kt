package pl.edu.amu.wmi.erykandroidcommon.base

import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.WindowManager
import android.view.WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
import android.widget.TextView
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import pl.edu.amu.wmi.erykandroidcommon.R
import pl.edu.amu.wmi.erykandroidcommon.di.CommonApplicationComponent
import pl.edu.amu.wmi.erykandroidcommon.di.CommonInjector
import pl.edu.amu.wmi.erykandroidcommon.ui.progress.ButteryProgressBar

/**
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@softra.pl> on 18.10.17.
 */
abstract class BaseActivity : AppCompatActivity(), BaseAdapter {

    private var progressBar: ButteryProgressBar? = null

    override fun showThrobber() {
        progressBar!!.visibility = VISIBLE
        window.setFlags(FLAG_NOT_TOUCHABLE, FLAG_NOT_TOUCHABLE)
    }

    override fun hideThrobber() {
        progressBar!!.visibility = INVISIBLE
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    fun <T> singleWithThrobber() = SingleTransformer<T, T> {
        it
            .doOnSubscribe { showThrobber() }
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally({ hideThrobber() })
    }

    abstract val progressColor: Int

    fun initIndeterminateProgress() {
        progressBar = ButteryProgressBar(this, null, progressColor)
        progressBar!!.layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, 24)
        progressBar!!.visibility = INVISIBLE
        val viewGroup = findViewById<ViewGroup>(android.R.id.content).getChildAt(0) as ViewGroup
        viewGroup.addView(progressBar, 0)
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
