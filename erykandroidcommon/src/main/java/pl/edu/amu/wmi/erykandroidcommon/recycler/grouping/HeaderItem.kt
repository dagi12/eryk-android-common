package pl.edu.amu.wmi.erykandroidcommon.recycler.grouping;

import lombok.Getter;

public class HeaderItem implements ListItem {

    @Getter
    private String header;

    public HeaderItem(String header) {
        this.header = header;
    }


    @Override
    public ListItemType getType() {
        return ListItemType.TYPE_HEADER;
    }

}
