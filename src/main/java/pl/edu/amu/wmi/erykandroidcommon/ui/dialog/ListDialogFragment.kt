package pl.edu.amu.wmi.erykandroidcommon.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import pl.edu.amu.wmi.erykandroidcommon.R
import pl.edu.amu.wmi.erykandroidcommon.adapter.StringActionAdapter
import pl.edu.amu.wmi.erykandroidcommon.exception.AdapterLackException

/**
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@247.codes> on 21.11.17.
 */
const val LIST = "LIST"

open class ListDialogFragment : DialogFragment() {

    lateinit var arr: ArrayList<String>

    private lateinit var actionAdaper: StringActionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arr = arguments.getStringArrayList(LIST)
        if (activity is StringActionAdapter) {
            actionAdaper = activity as StringActionAdapter
        } else {
            AdapterLackException(activity, StringActionAdapter::class.java)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog = AlertDialog
        .Builder(activity)
        .setTitle(getString(R.string.choose_option))
        .setItems(arr.toTypedArray()) { dialogInterface, i ->
            dialogInterface.dismiss()
            actionAdaper.action(arr[i])
        }
        .setNegativeButton(android.R.string.cancel) { dialogInterface, _ ->
            dialogInterface.dismiss()
        }
        .create()
}
