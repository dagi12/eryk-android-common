package pl.edu.amu.wmi.erykandroidcommon.util;

import java.util.LinkedList;
import java.util.List;

public final class ListUtils {

    private static final String[] EMPTY_STRING_ARRAY = new String[0];

    private ListUtils() {
    }

    public static boolean isEmpty(List list) {
        return list == null || list.isEmpty();
    }

    public static String[] remove(String[] arr, int pos) {
        List<String> result = new LinkedList<>();

        for (int i = 0; i < arr.length; ++i) {
            if (i != pos) {
                result.add(arr[i]);
            }
        }
        return result.toArray(EMPTY_STRING_ARRAY);
    }

}


