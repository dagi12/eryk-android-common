package pl.edu.amu.wmi.erykandroidcommon.ui.dialog

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.support.v4.app.FragmentManager
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatDialogFragment
import android.text.Editable
import android.text.TextWatcher
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

import io.reactivex.Maybe
import io.reactivex.subjects.MaybeSubject
import lombok.NonNull

/**
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@247.codes> on 28.07.17.
 */
abstract class EditTextDialogFragment(@field:StringRes
                                      private val title: Int, @field:LayoutRes
                                      private val layoutFragment: Int, @field:IdRes
                                      private val targetEditText: Int) : AppCompatDialogFragment() {

    private var dialog: AlertDialog? = null

    private var okButton: Button? = null

    private var dialogResultStream: MaybeSubject<String>? = null

    private var target: EditText? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        dialog = AlertDialog.Builder(context)
                .setTitle(title)
                .setView(layoutFragment)
                .setPositiveButton(android.R.string.ok) { dialogInterface, i -> attemptClose(target) }
                .setNegativeButton(android.R.string.cancel) { dialogInterface, i ->
                    dialogInterface.dismiss()
                    dialogResultStream!!.onComplete()
                    setKeyboardVisible(false)
                }
                .create()

        dialog!!.setOnShowListener { dialogInterface ->
            target = dialog!!.findViewById(targetEditText)
            okButton = dialog!!.getButton(DialogInterface.BUTTON_POSITIVE)
            okButton!!.isEnabled = false
            target!!.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(charSequence: CharSequence, i: Int,
                                               i1: Int, i2: Int) {
                    // no need
                }

                override fun onTextChanged(charSequence: CharSequence, i: Int,
                                           i1: Int, i2: Int) {
                    // no need
                }

                override fun afterTextChanged(editable: Editable) {
                    okButton!!.isEnabled = !editable.toString().isEmpty()
                }
            })
            target!!.setOnEditorActionListener { textView, actionId, keyEvent ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    attemptClose(textView)
                }
                true
            }
        }

        return dialog
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setKeyboardVisible(true)
    }

    @Synchronized
    fun show(@NonNull fragmentManager: FragmentManager): Maybe<String> {
        if (dialogResultStream == null) {
            dialogResultStream = MaybeSubject.create()
            show(fragmentManager, javaClass.simpleName)
        }
        return dialogResultStream
    }

    private fun setKeyboardVisible(visible: Boolean) {
        val mode = if (visible)
            WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE
        else
            WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN
        val window = getDialog().window
        window?.setSoftInputMode(mode)
    }

    private fun attemptClose(@NonNull textView: TextView?) {
        val email = textView!!.text.toString()
        if (!email.isEmpty()) {
            setKeyboardVisible(false)
            dialog!!.dismiss()
            dialogResultStream!!.onSuccess(target!!.text.toString())
        }
    }
}
