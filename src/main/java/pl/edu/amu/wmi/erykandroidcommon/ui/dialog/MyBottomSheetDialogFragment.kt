package pl.edu.amu.wmi.erykandroidcommon.ui.dialog

import android.app.Dialog
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialog
import android.support.v7.app.AppCompatDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import pl.edu.amu.wmi.erykandroidcommon.R

abstract class MyBottomSheetDialogFragment(@LayoutRes private val layoutRes: Int? = null) : AppCompatDialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val sheetDialog = BottomSheetDialog(activity!!, R.style.Common_BottomSheet)
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (layoutRes != null) {
            return inflater.inflate(layoutRes, container, false)
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

}
