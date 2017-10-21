package pl.edu.amu.wmi.erykandroidcommon.ui.dialog

import android.app.Dialog
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialog
import android.support.v7.app.AppCompatDialogFragment
import android.widget.FrameLayout

import pl.edu.amu.wmi.erykandroidcommon.R


class MyBottomSheetDialogFragment : AppCompatDialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val sheetDialog = BottomSheetDialog(activity, R.style.AppTheme_BottomSheet)
        sheetDialog.setOnShowListener { dialogInterface ->
            val bottomSheetDialog = dialogInterface as BottomSheetDialog
            val bottomSheetLayout = bottomSheetDialog.findViewById<FrameLayout>(android.support.design.R.id.design_bottom_sheet)
            if (bottomSheetLayout != null) {
                BottomSheetBehavior
                        .from(bottomSheetLayout).state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        return sheetDialog
    }
}
