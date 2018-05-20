package pl.edu.amu.wmi.erykandroidcommon.verify

import android.app.AlertDialog
import android.content.Context

class FormVerificationManager(private val fieldVerifier: FieldVerifier, private val context: Context) {

    fun verify(): Boolean {
        val verificationResult = fieldVerifier.verify()
        if (verificationResult != null) {
            val alertDialog = AlertDialog.Builder(context).create()
            alertDialog.setMessage(verificationResult.errorMessage)
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE,
                context.getString(android.R.string.ok)
            ) { dialogInterface, _ -> dialogInterface.dismiss() }
            alertDialog.show()
            return !verificationResult.isFailure
        }
        return true
    }
}
