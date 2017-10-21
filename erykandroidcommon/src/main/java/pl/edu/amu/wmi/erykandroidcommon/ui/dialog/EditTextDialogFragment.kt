package pl.edu.amu.wmi.erykandroidcommon.ui.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import io.reactivex.Maybe;
import io.reactivex.subjects.MaybeSubject;
import lombok.NonNull;

/**
 * @author Eryk Mariankowski <eryk.mariankowski@247.codes> on 28.07.17.
 */
public abstract class EditTextDialogFragment extends AppCompatDialogFragment {

    @StringRes
    private final int title;

    @LayoutRes
    private final int layoutFragment;

    @IdRes
    private final int targetEditText;

    private AlertDialog dialog;

    private Button okButton;

    private MaybeSubject<String> dialogResultStream;

    private EditText target;

    public EditTextDialogFragment(int title, int layoutFragment, int targetEditText) {
        this.title = title;
        this.layoutFragment = layoutFragment;
        this.targetEditText = targetEditText;
    }

    @android.support.annotation.NonNull
    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        dialog = new AlertDialog.Builder(getContext())
                .setTitle(title)
                .setView(layoutFragment)
                .setPositiveButton(android.R.string.ok, (dialogInterface, i) -> attemptClose(target))
                .setNegativeButton(android.R.string.cancel, (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                    dialogResultStream.onComplete();
                    setKeyboardVisible(false);
                })
                .create();

        dialog.setOnShowListener(dialogInterface -> {
            target = dialog.findViewById(targetEditText);
            okButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
            okButton.setEnabled(false);
            target.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(final CharSequence charSequence, final int i,
                                              final int i1, final int i2) {
                    // no need
                }

                @Override
                public void onTextChanged(final CharSequence charSequence, final int i,
                                          final int i1, final int i2) {
                    // no need
                }

                @Override
                public void afterTextChanged(final Editable editable) {
                    okButton.setEnabled(!editable.toString().isEmpty());
                }
            });
            target.setOnEditorActionListener((textView, actionId, keyEvent) -> {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    attemptClose(textView);
                }
                return true;
            });
        });

        return dialog;
    }

    @Override
    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setKeyboardVisible(true);
    }

    public synchronized Maybe<String> show(@NonNull final FragmentManager fragmentManager) {
        if (dialogResultStream == null) {
            dialogResultStream = MaybeSubject.create();
            show(fragmentManager, getClass().getSimpleName());
        }
        return dialogResultStream;
    }

    private void setKeyboardVisible(final boolean visible) {
        final int mode = visible ? WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE
                : WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN;
        final Window window = getDialog().getWindow();
        if (window != null) {
            window.setSoftInputMode(mode);
        }
    }

    private void attemptClose(@NonNull final TextView textView) {
        final String email = textView.getText().toString();
        if (!email.isEmpty()) {
            setKeyboardVisible(false);
            dialog.dismiss();
            dialogResultStream.onSuccess(target.getText().toString());
        }
    }
}
