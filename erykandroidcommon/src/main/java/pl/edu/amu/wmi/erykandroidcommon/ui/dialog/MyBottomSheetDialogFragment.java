package pl.edu.amu.wmi.erykandroidcommon.ui.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
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
        BottomSheetDialog dialog = new BottomSheetDialog(getActivity(), R.style.AppTheme_BottomSheet);
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) dialog;
                FrameLayout bottomSheetLayout = (FrameLayout) bottomSheetDialog.findViewById(android.support.design.R.id.design_bottom_sheet);
                if (bottomSheetLayout != null) {
                    BottomSheetBehavior
                            .from(bottomSheetLayout)
                            .setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            }
        });
        return dialog;
    }
}
