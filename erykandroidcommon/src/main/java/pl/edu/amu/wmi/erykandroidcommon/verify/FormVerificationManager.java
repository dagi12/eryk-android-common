package pl.edu.amu.wmi.erykandroidcommon.verify;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;

import static android.text.TextUtils.isEmpty;

class FormVerificationManager {

    private final Context context;
    private final FieldVerifier fieldVerifier;

    FormVerificationManager(Context context, @NonNull FieldVerifier fieldVerifier) {
        this.context = context;
        this.fieldVerifier = fieldVerifier;
    }

    boolean verify() {
        VerificationResult verificationResult = fieldVerifier.verify();
        if (verificationResult != null) {
            if (!isEmpty(verificationResult.getErrorMessage())) {
                AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setMessage(verificationResult.getErrorMessage());
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, context.getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                alertDialog.show();
                return false;
            } else if (verificationResult.isFailure()) {
                return false;
            }
        }
        return true;
    }


}
