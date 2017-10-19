package pl.edu.amu.wmi.erykandroidcommon.util;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;


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
