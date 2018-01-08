package pl.edu.amu.wmi.erykandroidcommon.exception

import android.content.Context

/**
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@247.codes> on 26.07.17.
 */
class NotImplementedException(context: Context, aClass: Class<*>) :
    ClassCastException(context.toString() + " must implement " + aClass.toString() + ".")
