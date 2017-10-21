package pl.edu.amu.wmi.erykandroidcommon.rx;

/**
 * @author Eryk Mariankowski <eryk.mariankowski@softra.pl> on 17.07.17.
 */
public class StringUtils {

    private StringUtils() {
    }

    public static String addLeadingZeros(int i, int len) {
        return String.format("%0" + len + "d", i);
    }


}
