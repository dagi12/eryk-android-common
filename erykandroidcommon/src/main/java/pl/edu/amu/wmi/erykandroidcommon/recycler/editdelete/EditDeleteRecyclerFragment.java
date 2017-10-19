package pl.edu.amu.wmi.erykandroidcommon.recycler.editdelete;


import lombok.NonNull;
import pl.edu.amu.wmi.erykandroidcommon.recycler.delete.DeleteRecyclerFragment;

/**
 * @author Eryk Mariankowski <eryk.mariankowski@247.codes> on 28.07.17.
 */
public abstract class EditDeleteRecyclerFragment<T, S extends EditDeleteItemViewHolder<T>> extends DeleteRecyclerFragment<T, S> {

    @SuppressWarnings("unchecked")
    @Override
    public void initAdapter() {
        super.initAdapter();
//        ((EditDeleteViewAdapter<T, S>) adapter).getEditClicks().subscribe(this::edit);
    }

    protected abstract void edit(@NonNull final T item);
}
