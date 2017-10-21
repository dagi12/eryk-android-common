package pl.edu.amu.wmi.erykandroidcommon.exception;

import android.content.Context;

public class AdapterLackException extends RuntimeException {

    private static final String MESSAGE_PART = " must implement ";

    public <T> AdapterLackException(Context context, Class<T> adapter) {
        super(context.toString() + MESSAGE_PART + adapter.getName());
    }
}
