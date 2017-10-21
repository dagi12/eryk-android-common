package pl.edu.amu.wmi.erykandroidcommon.recycler.grouping

import android.content.Context

import java.util.ArrayList
import java.util.Calendar
import java.util.EnumMap

import pl.edu.amu.wmi.erykandroidcommon.R
import pl.edu.amu.wmi.erykandroidcommon.util.DateUtils

internal class GroupingService(private val context: Context) {

    private var groupingDayEnumMap: EnumMap<GroupingDay, String>? = null

    private fun getDayOfWeekLabel(dayOfWeek: Int, context: Context): String? {
        when (dayOfWeek) {
            Calendar.MONDAY -> return context.getString(R.string.monday)
            Calendar.TUESDAY -> return context.getString(R.string.tuesday)
            Calendar.WEDNESDAY -> return context.getString(R.string.wednesday)
            Calendar.THURSDAY -> return context.getString(R.string.thursday)
            Calendar.FRIDAY -> return context.getString(R.string.friday)
            Calendar.SATURDAY -> return context.getString(R.string.saturday)
            Calendar.SUNDAY -> return context.getString(R.string.sunday)
            else -> return null
        }
    }

    private fun getGroupingDayFromDayDifference(dayDifference: Int): GroupingDay {
        when (dayDifference) {
            0 -> return GroupingDay.TODAY
            1 -> return GroupingDay.YESTERDAY
            2 -> return GroupingDay.GD2AGO
            3 -> return GroupingDay.GD3AGO
            4 -> return GroupingDay.GD4AGO
            5 -> return GroupingDay.GD5AGO
            else -> return GroupingDay.BEFORE
        }
    }

    private fun eKeylistToTree(timestampHolders: MutableList<TimestampHolder>): EnumMap<GroupingDay, MutableList<TimestampHolder>> {
        val groupingDayListEnumMap = EnumMap<GroupingDay, MutableList<TimestampHolder>>(GroupingDay::class.java)
        for (timestampHolder in timestampHolders) {
            val dayDifference = DateUtils.dayDifferenceToday(timestampHolder.timestamp!!)
            add(groupingDayListEnumMap, timestampHolder, getGroupingDayFromDayDifference(dayDifference))
        }
        return groupingDayListEnumMap
    }

    private fun add(map: EnumMap<GroupingDay, MutableList<TimestampHolder>>, timestampHolder: TimestampHolder, groupingDay: GroupingDay) {
        val list = map[groupingDay]
        list!!.add(timestampHolder)
    }

    private fun treeToListItemList(groupingDayListEnumMap: EnumMap<GroupingDay, MutableList<TimestampHolder>>, groupingDayEnumMap: EnumMap<GroupingDay, String>?): MutableList<ListItem> {
        val mItems = ArrayList<ListItem>()
        for ((key, value) in groupingDayListEnumMap) {
            val header = HeaderItem(groupingDayEnumMap!![key]!!)
            mItems.add(header)
            mItems.addAll(value)
        }
        return mItems
    }

    private fun init() {
        val calendar = Calendar.getInstance()
        groupingDayEnumMap = EnumMap(GroupingDay::class.java)
        groupingDayEnumMap!!.put(GroupingDay.TODAY, context.getString(R.string.today))
        groupingDayEnumMap!!.put(GroupingDay.BEFORE, context.getString(R.string.before))
        groupingDayEnumMap!!.put(GroupingDay.YESTERDAY, context.getString(R.string.yesterday))
        calendar.add(Calendar.DATE, -2)
        groupingDayEnumMap!!.put(GroupingDay.GD2AGO, getDayOfWeekLabel(calendar.get(Calendar.DAY_OF_WEEK), context))
        calendar.add(Calendar.DATE, -1)
        groupingDayEnumMap!!.put(GroupingDay.GD3AGO, getDayOfWeekLabel(calendar.get(Calendar.DAY_OF_WEEK), context))
        calendar.add(Calendar.DATE, -1)
        groupingDayEnumMap!!.put(GroupingDay.GD4AGO, getDayOfWeekLabel(calendar.get(Calendar.DAY_OF_WEEK), context))
        calendar.add(Calendar.DATE, -1)
        groupingDayEnumMap!!.put(GroupingDay.GD5AGO, getDayOfWeekLabel(calendar.get(Calendar.DAY_OF_WEEK), context))
    }

    fun process(list: MutableList<TimestampHolder>): List<ListItem> {
        init()
        val enumMap = eKeylistToTree(list)
        return treeToListItemList(enumMap, groupingDayEnumMap)
    }

}


