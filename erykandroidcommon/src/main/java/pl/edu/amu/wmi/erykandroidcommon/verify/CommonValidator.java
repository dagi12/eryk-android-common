package pl.edu.amu.wmi.erykandroidcommon.verify;

import android.content.Context;
import android.util.Patterns;
import android.widget.EditText;

import java.util.regex.Pattern;

import pl.edu.amu.wmi.erykandroidcommon.R;

/**
 * @author Eryk Mariankowski <eryk.mariankowski@247.codes> on 25.07.17.
 */
class CommonValidator {

    public CommonValidator(Context context) {
        this.context = context;
    }

    private final Context context;

    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z0-9_-]+$");

    private static final int MIN_PASSWORD_LENGTH = 7;

    public boolean validateEmail(EditText email) {
        if (email == null) {
            return false;
        }
        email.setError(null);
        if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
            email.setError(context.getString(R.string.error_invalid_email));
            return false;
        }
        return true;
    }

    public boolean validateUsername(EditText usernameEditText) {
        if (usernameEditText == null) {
            return false;
        }

        usernameEditText.setError(null);
        final String username = usernameEditText.getText().toString();
        if (username.isEmpty()) {
            usernameEditText.setError(context.getString(R.string.error_empty_username));
            return false;
        } else if (!USERNAME_PATTERN.matcher(username).matches()) {
            usernameEditText.setError(context.getString(R.string.error_username_regex));
            return false;
        }
        return true;
    }

    public boolean validatePassword(EditText passwordEditText) {
        if (passwordEditText == null) {
            return false;
        }

        passwordEditText.setError(null);
        final String password = passwordEditText.getText().toString();
        if (passwordEditText.getText().toString().isEmpty()) {
            passwordEditText.setError(context.getString(R.string.error_empty_password));
            return false;
        } else if (password.length() < MIN_PASSWORD_LENGTH) {
            passwordEditText.setError(context.getString(R.string.error_password_too_short));
            return false;
        }
        return true;
    }

    public boolean validateRetypePassword(EditText etPassword, EditText etRepeatPassword) {
        if (etRepeatPassword == null) {
            return false;
        }

        etRepeatPassword.setError(null);
        final String password = etPassword.getText().toString();
        final String rePassword = etRepeatPassword.getText().toString();
        if (!password.equals(rePassword)) {
            etRepeatPassword.setError(context.getString(R.string.error_password_dont_match));
            return false;
        }
        return true;
    }


}
