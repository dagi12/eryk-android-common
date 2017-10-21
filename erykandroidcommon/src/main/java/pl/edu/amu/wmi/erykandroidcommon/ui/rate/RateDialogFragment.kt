package pl.edu.amu.wmi.erykandroidcommon.ui.rate

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_rate_dialog.*
import pl.edu.amu.wmi.erykandroidcommon.R
import pl.edu.amu.wmi.erykandroidcommon.exception.AdapterLackException
import pl.edu.amu.wmi.erykandroidcommon.ui.spinner.NumberSpinnerAdapter

class RateDialogFragment : DialogFragment() {


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
                .setPositiveButton(android.R.string.ok) { dialogInterface, i -> adapter!!.processVote(rate_spinner!!.selectedItem as Int) }

        val parent = activity.findViewById<ViewGroup>(android.R.id.content)
        val inflater = activity.layoutInflater
        val view = inflater.inflate(R.layout.fragment_rate_dialog, parent, false)
        builder.setView(view)
        val dialog = builder.create()
        rate_spinner!!.adapter = NumberSpinnerAdapter(activity,
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
