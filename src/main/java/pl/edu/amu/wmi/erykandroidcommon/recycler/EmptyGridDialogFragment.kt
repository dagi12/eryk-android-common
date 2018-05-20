package pl.edu.amu.wmi.erykandroidcommon.recycler

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import pl.edu.amu.wmi.erykandroidcommon.R

class EmptyGridDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle): Dialog {
        val builder = AlertDialog.Builder(activity, R.style.Common)
        builder
            .setTitle(getString(R.string.no_data))
            .setNegativeButton(android.R.string.ok) { dialogInterface, _ ->
                activity!!.finish()
                dialogInterface.dismiss()
            }

        return builder.create()
    }

    companion object {
        val instance: EmptyGridDialogFragment get() = EmptyGridDialogFragment()
    }
}
