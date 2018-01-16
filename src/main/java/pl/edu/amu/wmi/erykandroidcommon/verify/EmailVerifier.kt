package pl.edu.amu.wmi.erykandroidcommon.verify

import android.content.Context
import android.support.design.widget.TextInputLayout
import android.util.Patterns.EMAIL_ADDRESS
import pl.edu.amu.wmi.erykandroidcommon.R

/**
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@247.codes> on 20.11.17.
 */
class EmailVerifier(inputLayout: TextInputLayout, context: Context) :

    TextValidator(inputLayout, context.getString(R.string.error_invalid_email)) {

    override fun isCorrect(text: String): Boolean = EMAIL_ADDRESS.matcher(text).matches()
}