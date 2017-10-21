package pl.edu.amu.wmi.erykandroidcommon.recycler.grouping;

import lombok.Getter;

public enum ListItemType {

    TYPE_HEADER(0), TYPE_ITEM(1);

    @Getter
    private final int type;

    ListItemType(int type) {
        this.type = type;
    }

}
