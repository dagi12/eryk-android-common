package pl.edu.amu.wmi.erykandroidcommon.di;

import android.content.Context;

/**
 * @author Eryk Mariankowski <eryk.mariankowski@247.codes> on 21.10.17.
 */
public class InjectionUtil {

    CommonApplicationComponent getCommonComponent(Context context) {
        return ((CommonApplication) context.getApplicationContext())
                .commonApplicationComponent();
    }

}
