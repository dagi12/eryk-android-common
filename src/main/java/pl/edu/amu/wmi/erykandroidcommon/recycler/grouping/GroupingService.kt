package pl.edu.amu.wmi.erykandroidcommon.recycler.grouping

import android.content.Context
import pl.edu.amu.wmi.erykandroidcommon.R
import pl.edu.amu.wmi.erykandroidcommon.helper.DateUtils
import java.util.*

internal class GroupingService(private val context: Context) {

    private val groupingDayEnumMap: EnumMap<GroupingDay, String> = EnumMap(GroupingDay::class.java)

    private fun getDayOfWeekLabel(dayOfWeek: Int, context: Context): String? = when (dayOfWeek) {
        Calendar.MONDAY -> context.getString(R.string.monday)
        Calendar.TUESDAY -> context.getString(R.string.tuesday)
        Calendar.WEDNESDAY -> context.getString(R.string.wednesday)
        Calendar.THURSDAY -> context.getString(R.string.thursday)
        Calendar.FRIDAY -> context.getString(R.string.friday)
        Calendar.SATURDAY -> context.getString(R.string.saturday)
        Calendar.SUNDAY -> context.getString(R.string.sunday)
        else -> null
    }

    private fun getGroupingDayFromDayDifference(dayDifference: Int): GroupingDay = when (dayDifference) {
        0 -> GroupingDay.TODAY
        1 -> GroupingDay.YESTERDAY
        2 -> GroupingDay.GD2AGO
        3 -> GroupingDay.GD3AGO
        4 -> GroupingDay.GD4AGO
        5 -> GroupingDay.GD5AGO
        else -> GroupingDay.BEFORE
    }

    private fun eKeylistToTree(timestampHolders: List<TimestampHolder>): EnumMap<GroupingDay, MutableList<TimestampHolder>> {
        val groupingDayListEnumMap = EnumMap<GroupingDay, MutableList<TimestampHolder>>(GroupingDay::class.java)
        for (timestampHolder in timestampHolders) {
            val dayDifference = DateUtils.dayDifferenceToday(timestampHolder.timestamp)
            add(groupingDayListEnumMap, timestampHolder, getGroupingDayFromDayDifference(dayDifference))
        }
        return groupingDayListEnumMap
    }

    private fun add(map: EnumMap<GroupingDay, MutableList<TimestampHolder>>, timestampHolder: TimestampHolder, groupingDay: GroupingDay) =
        map[groupingDay]!!.add(timestampHolder)

    private fun treeToListItemList(groupingDayListEnumMap: EnumMap<GroupingDay, MutableList<TimestampHolder>>, groupingDayEnumMap: EnumMap<GroupingDay, String>): List<ListItem> {
        val mItems = ArrayList<ListItem>()
        for ((key, value) in groupingDayListEnumMap) {
            val header = HeaderItem(groupingDayEnumMap[key]!!)
            mItems.add(header)
            mItems.addAll(value)
        }
        return mItems
    }

    private fun init() {
        val calendar = Calendar.getInstance()
        groupingDayEnumMap.put(GroupingDay.TODAY, context.getString(R.string.today))
        groupingDayEnumMap.put(GroupingDay.BEFORE, context.getString(R.string.before))
        groupingDayEnumMap.put(GroupingDay.YESTERDAY, context.getString(R.string.yesterday))
        calendar.add(Calendar.DATE, -2)
        groupingDayEnumMap.put(GroupingDay.GD2AGO, getDayOfWeekLabel(calendar.get(Calendar.DAY_OF_WEEK), context))
        calendar.add(Calendar.DATE, -1)
        groupingDayEnumMap.put(GroupingDay.GD3AGO, getDayOfWeekLabel(calendar.get(Calendar.DAY_OF_WEEK), context))
        calendar.add(Calendar.DATE, -1)
        groupingDayEnumMap.put(GroupingDay.GD4AGO, getDayOfWeekLabel(calendar.get(Calendar.DAY_OF_WEEK), context))
        calendar.add(Calendar.DATE, -1)
        groupingDayEnumMap.put(GroupingDay.GD5AGO, getDayOfWeekLabel(calendar.get(Calendar.DAY_OF_WEEK), context))
    }

    fun process(list: List<TimestampHolder>): List<ListItem> {
        init()
        val enumMap = eKeylistToTree(list)
        return treeToListItemList(enumMap, groupingDayEnumMap)
    }
}


