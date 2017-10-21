package pl.edu.amu.wmi.erykandroidcommon.ui.rating

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView

import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnTouch
import pl.edu.amu.wmi.erykandroidcommon.R
import pl.edu.amu.wmi.erykandroidcommon.R2
import pl.edu.amu.wmi.erykandroidcommon.adapter.LoginSuccessAdapter
import pl.edu.amu.wmi.erykandroidcommon.exception.AdapterLackException

class RatingCountBar : Fragment() {

    @BindView(R2.id.rating)
    internal var ratingBar: RatingBar? = null

    @BindView(R2.id.rating_count)
    internal var ratingCountTextView: TextView? = null

    private var adapter: RatingCountBarAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize(activity)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle): View? {
        val view = inflater.inflate(R.layout.fragment_rating_count_bar, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    private fun initialize(context: Context) {
        if (context is LoginSuccessAdapter) {
            adapter = context as RatingCountBarAdapter
        } else {
            throw AdapterLackException(activity, RatingCountBarAdapter::class.java)
        }
    }

    fun setData(pair: RateCountPair?) {
        if (pair != null) {
            ratingBar!!.rating = pair.rating()
            ratingCountTextView!!.text = "(" + pair.count() + ")"
        }
    }

    @OnTouch(R2.id.rating)
    fun onTouchGymRatePreview(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_UP) {
            adapter!!.onClick()
        }
        return true
    }

    interface RatingCountBarAdapter {
        fun onClick()
    }

}
