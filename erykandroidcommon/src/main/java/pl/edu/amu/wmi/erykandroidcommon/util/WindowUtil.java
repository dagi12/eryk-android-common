package pl.edu.amu.wmi.erykandroidcommon.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import pl.edu.amu.wmi.erykandroidcommon.R;


public final class WindowUtil {

    private WindowUtil() {
    }

    public static void enableActionBar(Activity activity) {
        if (activity.getActionBar() != null) {
            activity.getActionBar().setDisplayHomeAsUpEnabled(true);
        } else if (activity instanceof AppCompatActivity) {
            AppCompatActivity appCompatActivity = (AppCompatActivity) activity;
            if (appCompatActivity.getSupportActionBar() != null) {
                appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }
    }
}
