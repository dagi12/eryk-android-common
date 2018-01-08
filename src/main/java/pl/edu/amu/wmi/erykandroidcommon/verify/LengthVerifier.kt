package pl.edu.amu.wmi.erykandroidcommon.verify

import android.support.design.widget.TextInputLayout

/**
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@247.codes> on 20.11.17.
 */
class LengthVerifier(private val length: Int, inputLayout: TextInputLayout, errMsg: String) : TextValidator(inputLayout, errMsg) {

    override fun isCorrect(text: String): Boolean = text.length >= length
}

