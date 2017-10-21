package pl.edu.amu.wmi.erykandroidcommon.recycler.grouping;

import pl.edu.amu.wmi.erykandroidcommon.R
import java.util.*
import java.util.List

class GroupingService {

    private final Context context;

    private EnumMap<GroupingDay, String> groupingDayEnumMap;

    public GroupingService(Context context) {
        this.context = context;
    }

    private static String getDayOfWeekLabel(int dayOfWeek, Context context) {
        switch (dayOfWeek) {
            case Calendar.MONDAY:
                return context.getString(R.string.monday);
            case Calendar.TUESDAY:
                return context.getString(R.string.tuesday);
            case Calendar.WEDNESDAY:
                return context.getString(R.string.wednesday);
            case Calendar.THURSDAY:
                return context.getString(R.string.thursday);
            case Calendar.FRIDAY:
                return context.getString(R.string.friday);
            case Calendar.SATURDAY:
                return context.getString(R.string.saturday);
            case Calendar.SUNDAY:
                return context.getString(R.string.sunday);
            default:
                return null;
        }
    }

    private static GroupingDay getGroupingDayFromDayDifference(int dayDifference) {
        switch (dayDifference) {
            case 0:
                return GroupingDay.TODAY;
            case 1:
                return GroupingDay.YESTERDAY;
            case 2:
                return GroupingDay.GD2AGO;
            case 3:
                return GroupingDay.GD3AGO;
            case 4:
                return GroupingDay.GD4AGO;
            case 5:
                return GroupingDay.GD5AGO;
            default:
                return GroupingDay.BEFORE;
        }
    }

    private static EnumMap<GroupingDay, List<TimestampHolder>> eKeylistToTree(List<? extends TimestampHolder> timestampHolders) {
        EnumMap<GroupingDay, List<TimestampHolder>> groupingDayListEnumMap = new EnumMap<>(GroupingDay.class);
        for (TimestampHolder timestampHolder : timestampHolders) {
            int dayDifference = DateUtils.dayDifferenceToday(timestampHolder.getTimestamp());
            add(groupingDayListEnumMap, timestampHolder, getGroupingDayFromDayDifference(dayDifference));
        }
        return groupingDayListEnumMap;
    }

    private static void add(EnumMap<GroupingDay, List<TimestampHolder>> map, TimestampHolder timestampHolder, GroupingDay groupingDay) {
        List<TimestampHolder> list = map.get(groupingDay);
        list.add(timestampHolder);
    }

    private static List<ListItem> treeToListItemList(EnumMap<GroupingDay, List<TimestampHolder>> groupingDayListEnumMap, EnumMap<GroupingDay, String> groupingDayEnumMap) {
        List<ListItem> mItems = new ArrayList<>();
        for (Map.Entry<GroupingDay, List<TimestampHolder>> entry : groupingDayListEnumMap.entrySet()) {
            HeaderItem header = new HeaderItem(groupingDayEnumMap.get(entry.getKey()));
            mItems.add(header);
            mItems.addAll(entry.getValue());
        }
        return mItems;
    }

    private void init() {
        Calendar calendar = Calendar.getInstance();
        groupingDayEnumMap = new EnumMap<>(GroupingDay.class);
        groupingDayEnumMap.put(GroupingDay.TODAY, context.getString(R.string.today));
        groupingDayEnumMap.put(GroupingDay.BEFORE, context.getString(R.string.before));
        groupingDayEnumMap.put(GroupingDay.YESTERDAY, context.getString(R.string.yesterday));
        calendar.add(Calendar.DATE, -2);
        groupingDayEnumMap.put(GroupingDay.GD2AGO, getDayOfWeekLabel(calendar.get(Calendar.DAY_OF_WEEK), context));
        calendar.add(Calendar.DATE, -1);
        groupingDayEnumMap.put(GroupingDay.GD3AGO, getDayOfWeekLabel(calendar.get(Calendar.DAY_OF_WEEK), context));
        calendar.add(Calendar.DATE, -1);
        groupingDayEnumMap.put(GroupingDay.GD4AGO, getDayOfWeekLabel(calendar.get(Calendar.DAY_OF_WEEK), context));
        calendar.add(Calendar.DATE, -1);
        groupingDayEnumMap.put(GroupingDay.GD5AGO, getDayOfWeekLabel(calendar.get(Calendar.DAY_OF_WEEK), context));
    }

    public List<ListItem> process(List<? extends TimestampHolder> list) {
        init();
        EnumMap<GroupingDay, List<TimestampHolder>> enumMap = eKeylistToTree(list);
        return treeToListItemList(enumMap, groupingDayEnumMap);
    }

}


