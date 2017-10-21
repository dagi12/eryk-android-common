package pl.edu.amu.wmi.erykandroidcommon.util

import java.util.LinkedList

object ListUtils {

    private val EMPTY_STRING_ARRAY = arrayOfNulls<String>(0)

    fun isEmpty(list: List<*>?): Boolean {
        return list == null || list.isEmpty()
    }

    fun remove(arr: Array<String>, pos: Int): Array<String> {
        val result = LinkedList<String>()

        for (i in arr.indices) {
            if (i != pos) {
                result.add(arr[i])
            }
        }
        return result.toTypedArray()
    }

}


