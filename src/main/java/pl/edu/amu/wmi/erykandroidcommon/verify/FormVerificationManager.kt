package pl.edu.amu.wmi.erykandroidcommon.verify

import android.app.AlertDialog
import android.content.Context

import android.text.TextUtils.isEmpty
import pl.edu.amu.wmi.erykandroidcommon.di.CommonApplication.Companion.commonGraph
import javax.inject.Inject

class FormVerificationManager(private val fieldVerifier: FieldVerifier) {

    @Inject
    lateinit var context: Context

    init {
        commonGraph.inject(this)
    }

    fun verify(): Boolean {
        val verificationResult = fieldVerifier.verify()
        if (!isEmpty(verificationResult.errorMessage)) {
            val alertDialog = AlertDialog.Builder(context).create()
            alertDialog.setMessage(verificationResult.errorMessage)
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE,
                context.getString(android.R.string.ok)
            ) { dialogInterface, _ -> dialogInterface.dismiss() }
            alertDialog.show()
            return false
        } else if (verificationResult.isFailure) {
            return false
        }
        return true
    }
}
