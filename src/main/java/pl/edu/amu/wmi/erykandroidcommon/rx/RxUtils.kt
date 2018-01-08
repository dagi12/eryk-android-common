package pl.edu.amu.wmi.erykandroidcommon.rx

import android.content.Context
import android.preference.PreferenceManager
import com.f2prateek.rx.preferences2.RxSharedPreferences

/**
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@softra.pl> on 19.07.17.
 */
internal object RxUtils {

    fun getSharedPreferences(mContext: Context): RxSharedPreferences =
        RxSharedPreferences.create(
            PreferenceManager.getDefaultSharedPreferences(mContext))

    fun getSharedPreferences(mContext: Context, prefsName: String): RxSharedPreferences =
        RxSharedPreferences.create(
            mContext.getSharedPreferences(prefsName, Context.MODE_PRIVATE))
}
