package pl.edu.amu.wmi.erykandroidcommon.service;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


class NetworkService {

    private NetworkService() {
    }

    private static boolean isOffline(Context application) {
        ConnectivityManager cm =
                (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return !(netInfo != null && netInfo.isConnectedOrConnecting());
    }

    public static boolean isOnline(Context application) {
        return !isOffline(application);
    }
}
