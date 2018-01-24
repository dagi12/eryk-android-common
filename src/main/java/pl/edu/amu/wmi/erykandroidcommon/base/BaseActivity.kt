package pl.edu.amu.wmi.erykandroidcommon.base

import android.app.Application
import android.support.v7.app.AppCompatActivity
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.WindowManager
import android.view.WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import pl.edu.amu.wmi.erykandroidcommon.di.CommonApplicationComponent
import pl.edu.amu.wmi.erykandroidcommon.di.CommonInjector
import pl.edu.amu.wmi.erykandroidcommon.ui.progress.ButteryProgressBar
import javax.inject.Inject

/**
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@softra.pl> on 18.10.17.
 */
abstract class BaseActivity : AppCompatActivity(), BaseAdapter {

    @Inject
    lateinit var commonApplication: Application

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

    val commonGraph: CommonApplicationComponent
        get() = CommonInjector.commonGraph
}
