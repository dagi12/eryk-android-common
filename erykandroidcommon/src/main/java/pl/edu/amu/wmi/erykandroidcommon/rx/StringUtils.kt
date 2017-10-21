package pl.edu.amu.wmi.erykandroidcommon.rx

/**
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@softra.pl> on 17.07.17.
 */
object StringUtils {

    fun addLeadingZeros(i: Int, len: Int): String {
        return String.format("%0" + len + "d", i)
    }


}
