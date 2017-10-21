package pl.edu.amu.wmi.erykandroidcommon.location;

import android.content.Context;
import android.location.LocationManager;

/**
 * @author Eryk Mariankowski <eryk.mariankowski@softra.pl> on 16.08.17.
 */
public class LocationService {

    private final Context context;

    public LocationService(Context context) {
        this.context = context;
    }

    public boolean isLocationOn() {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

}
