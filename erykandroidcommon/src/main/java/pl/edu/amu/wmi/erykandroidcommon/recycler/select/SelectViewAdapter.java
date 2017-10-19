package pl.edu.amu.wmi.erykandroidcommon.recycler.select;

import pl.edu.amu.wmi.erykandroidcommon.recycler.basic.BasicViewAdapter;
import pl.edu.amu.wmi.erykandroidcommon.util.ListUtils;

/**
 * @author Eryk Mariankowski <eryk.mariankowski@247.codes> on 31.08.17.
 */
public abstract class SelectViewAdapter<T, S extends SelectItemViewHolder<T>> extends BasicViewAdapter<T, S> {

    private final OnFirstSelectListener listener;

    private int selectedItem = -1;

//    private RadioButton lastChecked = null;

    public SelectViewAdapter(OnFirstSelectListener listener) {
        this.listener = listener;
    }

//    private void firstSelect() {
//        listener.firstSelect();
//    }

    T getSelectedItem() {
        if (selectedItem != -1 && !ListUtils.isEmpty(items)) {
            return items.get(selectedItem);
        }
        return null;
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

    public interface OnFirstSelectListener {
        void firstSelect();
    }

}
