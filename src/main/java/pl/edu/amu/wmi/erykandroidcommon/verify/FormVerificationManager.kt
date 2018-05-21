package pl.edu.amu.wmi.erykandroidcommon.verify

import android.app.Activity
import android.app.AlertDialog
import android.text.TextUtils.isEmpty

class FormVerificationManager(private val verifier: FieldVerifier, private val context: Activity) {

    fun verify(): Boolean {
        val verificationResult = verifier.verify()
        if (verificationResult != null) {
            if (!isEmpty(verificationResult.errorMessage)) {
                val alertDialog = AlertDialog.Builder(context).create()
                alertDialog.setMessage(verificationResult.errorMessage)
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, context.getString(android.R.string.ok)) { dialogInterface, i -> dialogInterface.dismiss() }
                alertDialog.show()
                return false
            } else if (verificationResult.isFailure) {
                return false
            }
        }
        return true
    }
}
