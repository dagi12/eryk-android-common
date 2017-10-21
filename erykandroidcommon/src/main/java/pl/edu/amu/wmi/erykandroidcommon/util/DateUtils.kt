package pl.edu.amu.wmi.erykandroidcommon.util;

import android.annotation.SuppressLint
import pl.edu.amu.wmi.erykandroidcommon.rx.StringUtils
import java.text.SimpleDateFormat
import java.util.*

public class DateUtils {

    private static final long SECONDS_IN_MILLI = 1000;

    private static final long MINUTES_IN_MILLI;

    private static final long HOURS_IN_MILLI;

    private static final long DAYS_IN_MILLI;

    static {
        MINUTES_IN_MILLI = SECONDS_IN_MILLI * 60;
        HOURS_IN_MILLI = MINUTES_IN_MILLI * 60;
        DAYS_IN_MILLI = HOURS_IN_MILLI * 24;
    }

    private DateUtils() {

    }

    private static int dayDifference(long start, long end) {
        return (int) ((end - start) / DAYS_IN_MILLI);
    }

    private static Date getEndOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public static int dayDifferenceToday(long timestamp) {
        return dayDifference(eKeyTimestampToJavaTimestamp(timestamp), getEndOfDay(new Date()).getTime());
    }

    private static long eKeyTimestampToJavaTimestamp(long timestamp) {
        return timestamp * 1000;
    }

    public static String timeFromTimestamp(long timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(timestamp * 1000));
        return StringUtils.addLeadingZeros(calendar.get(Calendar.HOUR_OF_DAY), 2) + ":" + StringUtils.addLeadingZeros(calendar.get(Calendar.MINUTE), 2);
    }

    public static String getMonthAndDate(long timestamp) {
        // ios app format
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dayMonthDate = new SimpleDateFormat("dd. MMM.");
        return dayMonthDate.format(eKeyTimestampToJavaTimestamp(timestamp));
    }

    public static boolean lessThanMinutesAgo(Long timestamp, int minutes) {
        final Long currentTimestamp = System.currentTimeMillis() / 1000;
        return (currentTimestamp - timestamp < (60 * minutes));
    }


    public static boolean debounce(Long timestamp, long timeout) {
        final Long currentTimestamp = System.currentTimeMillis() / 1000;
        return ((currentTimestamp - timestamp) > timeout);
    }
}
