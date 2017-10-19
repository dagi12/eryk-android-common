package pl.edu.amu.wmi.erykandroidcommon.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

class MyRecyclerViewAdapter<T extends UniqueItem, S extends AbstractViewHolder<T>> extends RecyclerView.Adapter<S> {

    private final AbstractFragmentGrid<T, S> abstractFragmentGrid;

    private List<T> values = new ArrayList<>();

    MyRecyclerViewAdapter(AbstractFragmentGrid<T, S> abstractFragmentGrid) {
        this.abstractFragmentGrid = abstractFragmentGrid;
    }

    void setValues(List<T> values) {
        this.values = values;
    }

    void addValue(T value) {
        values.add(value);
    }


    @Override
    public S onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(abstractFragmentGrid.getItemViewId(), parent, false);
        return abstractFragmentGrid.createViewHolder(view);
    }

    @Override
    public void onBindViewHolder(S holder, int position) {
        abstractFragmentGrid.setListenerRow(holder, values.get(position));
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public T getItemByPosition(int position) {
        return values.get(position);
    }

    public void updateValue(T item) {
        for (int i = 0; i < values.size(); ++i) {
            T listItem = values.get(i);
            if (listItem.getId() == item.getId()) {
                values.set(i, item);
            }
        }
    }

    public void delete(int id) {
        for (int i = 0; i < values.size(); ++i) {
            T listItem = values.get(i);
            if (listItem.getId() == id) {
                values.remove(i);
            }
        }
    }
}
