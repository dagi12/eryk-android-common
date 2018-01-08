package pl.edu.amu.wmi.erykandroidcommon.verify

import android.support.design.widget.TextInputLayout
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher

/**
 * @author Eryk Mariankowski <eryk.mariankowski@247.codes> on 20.11.17.
 */
abstract class TextValidator(private val inputLayout: TextInputLayout, private val errMsg: String) : TextWatcher {

    abstract fun isCorrect(text: String): Boolean

    override fun afterTextChanged(editable: Editable?) {
        val correct = isCorrect(editable.toString())
        if (correct && !TextUtils.isEmpty(inputLayout.error)) {
            inputLayout.error = ""
        } else if (!correct) {
            inputLayout.error = errMsg
        }
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
}