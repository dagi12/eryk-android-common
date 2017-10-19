package pl.edu.amu.wmi.erykandroidcommon.exception;

import android.content.Context;

import lombok.NonNull;

/**
 * @author Eryk Mariankowski <eryk.mariankowski@247.codes> on 26.07.17.
 */
public class NotImplementedException extends ClassCastException {

    public NotImplementedException(@NonNull Context context, @NonNull Class aClass) {
        super(context.toString() + " must implement " + aClass.toString() + ".");
    }
}
