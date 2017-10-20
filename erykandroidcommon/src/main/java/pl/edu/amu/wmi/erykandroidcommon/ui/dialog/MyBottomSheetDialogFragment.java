package pl.edu.amu.wmi.erykandroidcommon.ui.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.widget.FrameLayout;

import pl.edu.amu.wmi.erykandroidcommon.R;


public class MyBottomSheetDialogFragment extends AppCompatDialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final BottomSheetDialog sheetDialog = new BottomSheetDialog(getActivity(), R.style.AppTheme_BottomSheet);
        sheetDialog.setOnShowListener(dialogInterface -> {
            final BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) dialogInterface;
            FrameLayout bottomSheetLayout = bottomSheetDialog.findViewById(android.support.design.R.id.design_bottom_sheet);
            if (bottomSheetLayout != null) {
                BottomSheetBehavior
                        .from(bottomSheetLayout)
                        .setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });
        return sheetDialog;
    }
}