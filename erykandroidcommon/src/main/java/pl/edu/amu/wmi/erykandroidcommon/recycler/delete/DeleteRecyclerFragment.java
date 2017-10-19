package pl.edu.amu.wmi.erykandroidcommon.recycler.delete;

import lombok.NonNull;
import pl.edu.amu.wmi.erykandroidcommon.recycler.basic.BasicRecyclerFragment;

/**
 * @author Eryk Mariankowski <eryk.mariankowski@247.codes> on 28.07.17.
 */
public abstract class DeleteRecyclerFragment<T, S extends DeleteItemViewHolder<T>> extends BasicRecyclerFragment<T, S> {

    @SuppressWarnings("unchecked")
    protected void initAdapter() {
        if (adapter == null) {
            adapter = getAdapter();
        }
        DeleteViewAdapter<T, S> deleteAdapter = (DeleteViewAdapter) adapter;
        deleteAdapter.getDeleteClicks().subscribe(this::delete);
    }

    protected abstract void delete(@NonNull final T item);

}
