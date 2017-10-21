package pl.edu.amu.wmi.erykandroidcommon.location

import android.content.Context
import android.location.LocationManager

/**
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@softra.pl> on 16.08.17.
 */
class LocationService(private val context: Context) {

    val isLocationOn: Boolean
        get() {
            val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        }

}
