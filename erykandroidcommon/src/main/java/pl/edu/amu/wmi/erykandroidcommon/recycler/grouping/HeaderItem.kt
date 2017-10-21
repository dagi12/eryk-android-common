package pl.edu.amu.wmi.erykandroidcommon.recycler.grouping

import lombok.Getter

class HeaderItem(@field:Getter
                 private val header: String) : ListItem {


    override val type: ListItemType
        get() = ListItemType.TYPE_HEADER

}
