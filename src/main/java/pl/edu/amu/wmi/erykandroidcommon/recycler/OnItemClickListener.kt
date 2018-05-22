package pl.edu.amu.wmi.erykandroidcommon.recycler

/**
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@247.codes> on 22.05.18.
 */
interface OnItemClickListener<T : UniqueItem> {
    fun onItemClick(item: T)
}
