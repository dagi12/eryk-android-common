package pl.edu.amu.wmi.erykandroidcommon.recycler.grouping;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import pl.edu.amu.wmi.erykandroidcommon.R;
import pl.edu.amu.wmi.erykandroidcommon.recycler.AbstractViewHolder;

public abstract class GroupedRecyclerAdapter<T extends ListItem, P extends View, U extends AbstractViewHolder<P>> extends RecyclerView.Adapter {

    private List<? extends ListItem> mItems = new ArrayList<>();

    public void setData(List<ListItem> items) {
        this.mItems = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).getType().type();
    }

    @Override
    public AbstractViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ListItemType.TYPE_HEADER.type()) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_grouping_header, parent, false);
            return new HeaderViewHolder(view);
        } else {
            return createMyViewHolder(parent);
        }
    }

    protected abstract AbstractViewHolder createMyViewHolder(ViewGroup parent);

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ListItem item = mItems.get(position);
        if (ListItemType.TYPE_HEADER.type() == item.getType().type()) {
            HeaderViewHolder holder = (HeaderViewHolder) viewHolder;
            holder.setRow();
        } else {
            bindViewHolder((U) viewHolder, (T) item);
        }
    }

    protected abstract void bindViewHolder(U viewHolder, T t);

}
