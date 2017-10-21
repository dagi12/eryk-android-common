package pl.edu.amu.wmi.erykandroidcommon.ui.rating

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_rating_count_bar.*
import pl.edu.amu.wmi.erykandroidcommon.R
import pl.edu.amu.wmi.erykandroidcommon.adapter.LoginSuccessAdapter
import pl.edu.amu.wmi.erykandroidcommon.exception.AdapterLackException

class RatingCountBar : Fragment() {

    private var adapter: RatingCountBarAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize(activity)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle): View? {
        val view = inflater.inflate(R.layout.fragment_rating_count_bar, container, false)
        rating.setOnTouchListener(::onTouch)
        return view
    }

    private fun initialize(context: Context) {
        adapter = if (context is LoginSuccessAdapter) {
            context as RatingCountBarAdapter
        } else {
            throw AdapterLackException(activity, RatingCountBarAdapter::class.java)
        }
    }

    fun setData(pair: RateCountPair?) {
        if (pair != null) {
            rating.rating = pair.rating
            ratingCount!!.text = "(" + pair.count + ")"
        }
    }

    private val onTouchListener = View.OnTouchListener { view, motionEvent ->
        if (motionEvent.action == MotionEvent.ACTION_UP) {
            adapter!!.onClick()
        }
        true
    }

    private fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            adapter!!.onClick()
        }
        return false
    }

    interface RatingCountBarAdapter {
        fun onClick()
    }

}
