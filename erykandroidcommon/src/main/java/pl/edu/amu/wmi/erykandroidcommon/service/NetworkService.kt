package pl.edu.amu.wmi.erykandroidcommon.service

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo


internal object NetworkService {

    private fun isOffline(application: Context): Boolean {
        val cm = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return !(netInfo != null && netInfo.isConnectedOrConnecting)
    }

    fun isOnline(application: Context): Boolean {
        return !isOffline(application)
    }
}
