package pl.edu.amu.wmi.erykandroidcommon.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import pl.edu.amu.wmi.erykandroidcommon.di.CommonApplication;
import pl.edu.amu.wmi.erykandroidcommon.di.CommonApplicationComponent;

/**
 * @author Eryk Mariankowski <eryk.mariankowski@softra.pl> on 18.10.17.
 */
public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeNotificationBarColor(this);
    }

    @ColorRes
    public abstract int notificationColor();

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    void changeNotificationBarColor(Activity activity) {
        if (Build.VERSION.SDK_INT >= 21) {
            activity
                    .getWindow()
                    .setStatusBarColor(ContextCompat.getColor(activity, notificationColor()));
        }
    }

    CommonApplicationComponent getCommonComponent() {
        CommonApplication commonApplication = (CommonApplication) getApplication();
        return commonApplication.commonApplicationComponent();
    }


}
