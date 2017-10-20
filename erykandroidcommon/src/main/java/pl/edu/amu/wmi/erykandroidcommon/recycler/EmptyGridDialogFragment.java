package pl.edu.amu.wmi.erykandroidcommon.recycler;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.NonNull;

import pl.edu.amu.wmi.erykandroidcommon.R;


public class EmptyGridDialogFragment extends DialogFragment {

    public static EmptyGridDialogFragment getInstance() {
        return new EmptyGridDialogFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AppTheme_MyAlertDialogStyle);
        builder
                .setTitle(getString(R.string.no_data))
                .setNegativeButton(android.R.string.ok, (dialogInterface, i) -> {
                    getActivity().finish();
                    dialogInterface.dismiss();
                });

        return builder.create();
    }


}