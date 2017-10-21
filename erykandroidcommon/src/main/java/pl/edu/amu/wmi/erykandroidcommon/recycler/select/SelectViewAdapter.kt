package pl.edu.amu.wmi.erykandroidcommon.recycler.select

import pl.edu.amu.wmi.erykandroidcommon.recycler.basic.BasicViewAdapter
import pl.edu.amu.wmi.erykandroidcommon.util.ListUtils

/**
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@247.codes> on 31.08.17.
 */
abstract class SelectViewAdapter<T, S : SelectItemViewHolder<T>>
//    private RadioButton lastChecked = null;

(private val listener: OnFirstSelectListener) : BasicViewAdapter<T, S>() {

    private val selectedItem = -1

    //    private void firstSelect() {
    //        listener.firstSelect();
    //    }

    internal fun getSelectedItem(): T? {
        return if (selectedItem != -1 && !ListUtils.isEmpty(items)) {
            items[selectedItem]
        } else null
    }

    //    @Override
    //    public void onBindViewHolder(RecyclerViewWrapper<S> holder, int position) {
    //        super.onBindViewHolder(holder, position);
    //        final RadioButton radioButton = holder.getView().radio();
    //        radioButton.setChecked(position == selectedItem);
    //    }

    //    @Override
    //    public RecyclerViewWrapper<S> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    //        RecyclerViewWrapper<S> wrapper = super.onCreateViewHolder(parent, viewType);
    //        S view = wrapper.getView();
    //        final RadioButton radioButton = view.radio();
    //        view.setOnClickListener(v -> {
    //            if (!radioButton.isChecked()) {
    //                if (selectedItem == -1) {
    //                    firstSelect();
    //                } else if (lastChecked != null) {
    //                    lastChecked.setChecked(false);
    //                }
    //                lastChecked = radioButton;
    //                radioButton.setChecked(true);
    //                selectedItem = wrapper.getAdapterPosition();
    //            }
    //        });
    //        return wrapper;
    //    }

    //    @SuppressWarnings("unchecked")
    //    @Override
    //    protected void bindRow(@NonNull S view, @NonNull T item) {
    //        view.bind(item);
    //    }

    interface OnFirstSelectListener {
        fun firstSelect()
    }

}
