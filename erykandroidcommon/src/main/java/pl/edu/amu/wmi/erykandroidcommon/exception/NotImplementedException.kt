package pl.edu.amu.wmi.erykandroidcommon.exception

import android.content.Context
import android.support.annotation.NonNull

/**
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@247.codes> on 26.07.17.
 */
class NotImplementedException(@NonNull context: Context, @NonNull aClass: Class<*>) :
        ClassCastException(context.toString() + " must implement " + aClass.toString() + ".")
