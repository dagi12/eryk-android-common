package pl.edu.amu.wmi.erykandroidcommon.util

import android.app.Activity
import android.support.v7.app.AppCompatActivity


object WindowUtil {

    fun enableActionBar(activity: Activity) {
        if (activity.actionBar != null) {
            activity.actionBar!!.setDisplayHomeAsUpEnabled(true)
        } else if (activity is AppCompatActivity) {
            if (activity.supportActionBar != null) {
                activity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            }
        }
    }
}
