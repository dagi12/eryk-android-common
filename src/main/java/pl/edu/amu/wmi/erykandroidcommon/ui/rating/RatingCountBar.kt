package pl.edu.amu.wmi.erykandroidcommon.ui.rating

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_rating_count_bar.*
import kotlinx.android.synthetic.main.fragment_rating_count_bar.view.*
import pl.edu.amu.wmi.erykandroidcommon.R
import pl.edu.amu.wmi.erykandroidcommon.adapter.LoginSuccessAdapter
import pl.edu.amu.wmi.erykandroidcommon.exception.AdapterLackException

class RatingCountBar : Fragment() {

    lateinit var adapter: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize(activity)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_rating_count_bar, container, false)
        view.rating.setOnTouchListener(onTouchListener)
        return view
    }

    private fun initialize(context: Context) {
        adapter = if (context is LoginSuccessAdapter) {
            context as Runnable
        } else {
            throw AdapterLackException(activity, Runnable::class.java)
        }
    }

    fun setData(pair: RateCountPair?) {
        pair?.let {
            rating.rating = it.rating!!
            ratingCount!!.text = activity.getString(R.string.parentheses, pair.count)
        }
    }

    private val onTouchListener = View.OnTouchListener { _, motionEvent ->
        if (motionEvent.action == MotionEvent.ACTION_UP) {
            adapter.run()
        }
        true
    }

    private fun onTouch(motionEvent: MotionEvent): Boolean {
        if (motionEvent.action == MotionEvent.ACTION_UP) {
            adapter.run()
        }
        return false
    }

}
