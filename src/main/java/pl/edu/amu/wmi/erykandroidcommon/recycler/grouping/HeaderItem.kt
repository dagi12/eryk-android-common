package pl.edu.amu.wmi.erykandroidcommon.recycler.grouping

class HeaderItem(private val header: String) : ListItem {

    override val type: ListItemType
        get() = ListItemType.TYPE_HEADER
}
