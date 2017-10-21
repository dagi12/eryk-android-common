package pl.edu.amu.wmi.erykandroidcommon.recycler.basic;

import lombok.NonNull
import pl.edu.amu.wmi.erykandroidcommon.util.ListUtils

public abstract class BasicViewAdapter<T, S extends AbstractViewHolder<T>> extends RecyclerView.Adapter {

    protected List<T> items;

    @Override
    public int getItemCount() {
        if (!ListUtils.isEmpty(items)) {
            return items.size();
        }
        return 0;
    }

    public void setData(@NonNull final List<T> items) {
        this.items = items;
        notifyDataSetChanged();
    }

}
