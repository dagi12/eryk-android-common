package pl.edu.amu.wmi.erykandroidcommon.recycler.grouping

import lombok.Getter

enum class ListItemType private constructor(@field:Getter
                                            private val type: Int) {

    TYPE_HEADER(0), TYPE_ITEM(1)

}
