package pl.edu.amu.wmi.erykandroidcommon.ui.rate

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner

import butterknife.BindView
import butterknife.ButterKnife
import pl.edu.amu.wmi.erykandroidcommon.R
import pl.edu.amu.wmi.erykandroidcommon.R2
import pl.edu.amu.wmi.erykandroidcommon.exception.AdapterLackException
import pl.edu.amu.wmi.erykandroidcommon.ui.spinner.NumberSpinnerAdapter

class RateDialogFragment : DialogFragment() {

    @BindView(R2.id.rate_spinner)
    internal var rateSpinner: Spinner? = null

    private var adapter: RateResultAdapter? = null

    private fun initialize(context: Context) {
        if (context is RateResultAdapter) {
            adapter = context
        } else {
            throw AdapterLackException(activity, RateResultAdapter::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize(activity)
    }

    override fun onCreateDialog(savedInstanceState: Bundle): Dialog {
        val builder = AlertDialog.Builder(activity,
                R.style.AppTheme_MyAlertDialogStyle)
        builder.setTitle(arguments.getString(VOTE_TITLE_PARAM))
                .setNegativeButton(android.R.string.cancel) { dialogInterface, i ->
                    // no need
                }
                .setPositiveButton(android.R.string.ok) { dialogInterface, i -> adapter!!.processVote(rateSpinner!!.selectedItem as Int) }

        val parent = activity.findViewById<ViewGroup>(android.R.id.content)
        val inflater = activity.layoutInflater
        val view = inflater.inflate(R.layout.fragment_rate_dialog, parent, false)
        builder.setView(view)
        val dialog = builder.create()
        ButterKnife.bind(this, view)
        rateSpinner!!.adapter = NumberSpinnerAdapter(activity,
                arguments.getInt(MAX_POINTS_PARAM))
        return dialog
    }

    interface RateResultAdapter {
        fun processVote(vote: Int)
    }

    companion object {

        private val VOTE_TITLE_PARAM = "VOTE_TITLE"

        private val MAX_POINTS_PARAM = "MAX_POINTS"

        fun getInstance(message: String, maxPoints: Int?): RateDialogFragment {
            val rateDialogFragment = RateDialogFragment()
            val args = Bundle()
            args.putString(VOTE_TITLE_PARAM, message)
            args.putInt(MAX_POINTS_PARAM, maxPoints!!)
            rateDialogFragment.arguments = args
            return rateDialogFragment
        }
    }

}
