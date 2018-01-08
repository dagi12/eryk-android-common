package pl.edu.amu.wmi.erykandroidcommon.helper

object ListUtils {

    private val EMPTY_STRING_ARRAY = arrayOfNulls<String>(0)

    fun isEmpty(list: List<*>?): Boolean = list == null || list.isEmpty()

    fun remove(arr: Array<String>, pos: Int): Array<String> =
        arr.indices
            .filter { it != pos }
            .mapTo(ArrayList()) { arr[it] }
            .toTypedArray()
}


