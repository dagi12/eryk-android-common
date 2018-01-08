package pl.edu.amu.wmi.erykandroidcommon.verify

import android.support.design.widget.TextInputLayout
import android.text.TextUtils.isEmpty

/**
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@247.codes> on 20.11.17.
 */
class EmptyTextVerifier(inputLayout: TextInputLayout, errMsg: String) : TextValidator(inputLayout, errMsg) {

    override fun isCorrect(text: String): Boolean = !isEmpty(text)
}