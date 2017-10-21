package pl.edu.amu.wmi.erykandroidcommon.recycler.select;

import android.content.Context
import butterknife.BindView
import butterknife.OnClick
import pl.edu.amu.wmi.erykandroidcommon.R2

/**
 * @author Eryk Mariankowski <eryk.mariankowski@247.codes> on 31.08.17.
 */
public abstract class SelectRecyclerFragment<T, S extends SelectItemViewHolder<T>> extends BasicRecyclerFragment<T, S> {

    @BindView(R2.id.btn_add)
    protected Button button;

    private SelectViewAdapter<T, S> selectAdapter;

    private OnAddClickedListener<T> addClickedListener;

    @OnClick(R2.id.btn_add)
    public void onButtonAddClick() {
        addClickedListener.onAddClicked(selectAdapter.getSelectedItem());
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            addClickedListener = (OnAddClickedListener<T>) context;
        } catch (ClassCastException e) {
            throw new NotImplementedException(context, OnAddClickedListener.class);
        }
    }

    public interface OnAddClickedListener<T> {
        void onAddClicked(T item);
    }

}
