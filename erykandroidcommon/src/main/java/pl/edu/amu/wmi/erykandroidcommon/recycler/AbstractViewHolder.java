package pl.edu.amu.wmi.erykandroidcommon.recycler;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import lombok.Getter;
import lombok.Setter;

public abstract class AbstractViewHolder<T> extends RecyclerView.ViewHolder {

    public final View view;

    @Getter
    @Setter
    protected T item;

    protected AbstractViewHolder(View itemView) {
        super(itemView);
        view = itemView;
    }

    protected static String concat(String leftStr, String rightStr) {
        return leftStr + " " + (TextUtils.isEmpty(rightStr) ? "" : rightStr);
    }

    protected abstract void setRow();

}
