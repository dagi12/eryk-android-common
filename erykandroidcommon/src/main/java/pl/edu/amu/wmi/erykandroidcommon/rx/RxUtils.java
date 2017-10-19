package pl.edu.amu.wmi.erykandroidcommon.rx;

import android.content.Context;
import android.preference.PreferenceManager;

import com.f2prateek.rx.preferences2.RxSharedPreferences;

import lombok.NonNull;

/**
 * @author Eryk Mariankowski <eryk.mariankowski@softra.pl> on 19.07.17.
 */
public class RxUtils {

    private RxUtils() {
    }

    public static RxSharedPreferences getSharedPreferences(@NonNull Context mContext) {
        return RxSharedPreferences.create(
                PreferenceManager.getDefaultSharedPreferences(mContext));
    }

    public static RxSharedPreferences getSharedPreferences(@NonNull Context mContext, @NonNull String prefsName) {
        return RxSharedPreferences.create(
                mContext.getSharedPreferences(prefsName, Context.MODE_PRIVATE));
    }


}
