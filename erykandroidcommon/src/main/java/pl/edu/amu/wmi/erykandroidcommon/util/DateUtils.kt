package pl.edu.amu.wmi.erykandroidcommon.util

import android.annotation.SuppressLint

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

import pl.edu.amu.wmi.erykandroidcommon.rx.StringUtils

object DateUtils {

    private val SECONDS_IN_MILLI: Long = 1000

    private val MINUTES_IN_MILLI: Long

    private val HOURS_IN_MILLI: Long

    private val DAYS_IN_MILLI: Long

    init {
        MINUTES_IN_MILLI = SECONDS_IN_MILLI * 60
        HOURS_IN_MILLI = MINUTES_IN_MILLI * 60
        DAYS_IN_MILLI = HOURS_IN_MILLI * 24
    }

    private fun dayDifference(start: Long, end: Long): Int {
        return ((end - start) / DAYS_IN_MILLI).toInt()
    }

    private fun getEndOfDay(date: Date): Date {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.set(Calendar.HOUR_OF_DAY, 23)
        calendar.set(Calendar.MINUTE, 59)
        calendar.set(Calendar.SECOND, 59)
        calendar.set(Calendar.MILLISECOND, 999)
        return calendar.time
    }

    fun dayDifferenceToday(timestamp: Long): Int {
        return dayDifference(eKeyTimestampToJavaTimestamp(timestamp), getEndOfDay(Date()).time)
    }

    private fun eKeyTimestampToJavaTimestamp(timestamp: Long): Long {
        return timestamp * 1000
    }

    fun timeFromTimestamp(timestamp: Long): String {
        val calendar = Calendar.getInstance()
        calendar.time = Date(timestamp * 1000)
        return StringUtils.addLeadingZeros(calendar.get(Calendar.HOUR_OF_DAY), 2) + ":" + StringUtils.addLeadingZeros(calendar.get(Calendar.MINUTE), 2)
    }

    fun getMonthAndDate(timestamp: Long): String {
        // ios app format
        @SuppressLint("SimpleDateFormat") val dayMonthDate = SimpleDateFormat("dd. MMM.")
        return dayMonthDate.format(eKeyTimestampToJavaTimestamp(timestamp))
    }

    fun lessThanMinutesAgo(timestamp: Long?, minutes: Int): Boolean {
        val currentTimestamp = System.currentTimeMillis() / 1000
        return currentTimestamp - timestamp!! < 60 * minutes
    }


    fun debounce(timestamp: Long?, timeout: Long): Boolean {
        val currentTimestamp = System.currentTimeMillis() / 1000
        return currentTimestamp - timestamp!! > timeout
    }
}
