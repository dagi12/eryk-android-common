package pl.edu.amu.wmi.erykandroidcommon.base;

import android.content.Context;
import android.support.annotation.StringRes;

/**
 * Base interface for {@link android.support.v4.app.Fragment} to {@link android.app.Activity}
 * communication.
 *
 * @author Eryk Mariankowski <eryk.mariankowski@softra.pl> on 24.03.2017.
 */
public interface BaseAdapter {

    void showThrobber(@StringRes int messageId, Object... formatArgs);

    void showThrobber(String message);

    void hideThrobber();

    void showKeyboard();

    void hideKeyboard();

    void goBack();

    Context getContext();
}
