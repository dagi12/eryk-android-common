package pl.edu.amu.wmi.erykandroidcommon.verify

import android.app.AlertDialog
import android.content.Context

import android.text.TextUtils.isEmpty

internal class FormVerificationManager(private val context: Context, private val fieldVerifier: FieldVerifier) {

    fun verify(): Boolean {
        val verificationResult = fieldVerifier.verify()
        if (verificationResult != null) {
            if (!isEmpty(verificationResult.errorMessage)) {
                val alertDialog = AlertDialog.Builder(context).create()
                alertDialog.setMessage(verificationResult.errorMessage)
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE,
                        context.getString(android.R.string.ok)
                ) { dialogInterface, i -> dialogInterface.dismiss() }
                alertDialog.show()
                return false
            } else if (verificationResult.isFailure) {
                return false
            }
        }
        return true
    }


}
