package pl.edu.amu.wmi.erykandroidcommon.verify

import android.content.Context
import android.util.Patterns
import android.widget.EditText
import pl.edu.amu.wmi.erykandroidcommon.R
import java.util.regex.Pattern

/**
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@247.codes> on 25.07.17.
 */
internal class CommonValidator(private val context: Context) {

    fun validateEmail(email: EditText?): Boolean {
        if (email == null) {
            return false
        }
        email.error = null
        if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()) {
            email.error = context.getString(R.string.error_invalid_email)
            return false
        }
        return true
    }

    fun validateUsername(usernameEditText: EditText?): Boolean {
        if (usernameEditText == null) {
            return false
        }

        usernameEditText.error = null
        val username = usernameEditText.text.toString()
        if (username.isEmpty()) {
            usernameEditText.error = context.getString(R.string.error_empty_username)
            return false
        } else if (!USERNAME_PATTERN.matcher(username).matches()) {
            usernameEditText.error = context.getString(R.string.error_username_regex)
            return false
        }
        return true
    }

    fun validatePassword(passwordEditText: EditText?): Boolean {
        if (passwordEditText == null) {
            return false
        }

        passwordEditText.error = null
        val password = passwordEditText.text.toString()
        if (passwordEditText.text.toString().isEmpty()) {
            passwordEditText.error = context.getString(R.string.error_empty_password)
            return false
        } else if (password.length < MIN_PASSWORD_LENGTH) {
            passwordEditText.error = context.getString(R.string.error_password_too_short)
            return false
        }
        return true
    }

    fun validateRetypePassword(etPassword: EditText, etRepeatPassword: EditText?): Boolean {
        if (etRepeatPassword == null) {
            return false
        }

        etRepeatPassword.error = null
        val password = etPassword.text.toString()
        val rePassword = etRepeatPassword.text.toString()
        if (password != rePassword) {
            etRepeatPassword.error = context.getString(R.string.error_password_dont_match)
            return false
        }
        return true
    }

    companion object {

        private val USERNAME_PATTERN = Pattern.compile("^[a-zA-Z0-9_-]+$")

        private val MIN_PASSWORD_LENGTH = 7
    }
}
