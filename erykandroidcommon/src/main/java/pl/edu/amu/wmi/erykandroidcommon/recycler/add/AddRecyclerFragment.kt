package pl.edu.amu.wmi.erykandroidcommon.recycler.add;


import lombok.NonNull;
import pl.edu.amu.wmi.erykandroidcommon.recycler.basic.BasicRecyclerFragment;

/**
 * @author Eryk Mariankowski <eryk.mariankowski@247.codes> on 28.07.17.
 */
public abstract class AddRecyclerFragment<T, S extends AddItemViewHolder<T>> extends BasicRecyclerFragment<T, S> {

    @SuppressWarnings("unchecked")
    public void initAdapter() {
        if (adapter == null) {
            adapter = getAdapter();
        }
        AddViewAdapter<T, S> addAdapter = (AddViewAdapter<T, S>) adapter;
        addAdapter.getAddClicks().subscribe(this::add);
    }

    protected abstract void add(@NonNull final T item);

}
