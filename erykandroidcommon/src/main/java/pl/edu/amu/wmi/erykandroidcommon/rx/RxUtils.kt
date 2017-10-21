package pl.edu.amu.wmi.erykandroidcommon.rx

import android.content.Context
import android.preference.PreferenceManager

import com.f2prateek.rx.preferences2.RxSharedPreferences

import lombok.NonNull

/**
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@softra.pl> on 19.07.17.
 */
internal object RxUtils {

    fun getSharedPreferences(@NonNull mContext: Context): RxSharedPreferences {
        return RxSharedPreferences.create(
                PreferenceManager.getDefaultSharedPreferences(mContext))
    }

    fun getSharedPreferences(@NonNull mContext: Context, @NonNull prefsName: String): RxSharedPreferences {
        return RxSharedPreferences.create(
                mContext.getSharedPreferences(prefsName, Context.MODE_PRIVATE))
    }


}
