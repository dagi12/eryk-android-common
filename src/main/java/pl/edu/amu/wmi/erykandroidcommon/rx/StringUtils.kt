package pl.edu.amu.wmi.erykandroidcommon.rx

import android.text.TextUtils
import android.webkit.URLUtil

/**
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@softra.pl> on 17.07.17.
 */
object StringUtils {

    fun addLeadingZeros(i: Int, len: Int): String = String.format("%0" + len + "d", i)

    fun addSlash(url: String): String {
        if (url[url.length - 1] != '/') {
            return url + "/"
        }
        return url
    }

    fun isValidUrl(url: String): Boolean = !TextUtils.isEmpty(url) && URLUtil.isValidUrl(url)

}
