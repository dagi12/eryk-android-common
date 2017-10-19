package pl.edu.amu.wmi.erykandroidcommon.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerViewAdapter<T extends UniqueItem, S extends AbstractViewHolder<T>> extends RecyclerView.Adapter<S> {

    private final AbstractFragmentGrid<T, S> abstractFragmentGrid;
    private List<T> mValues = new ArrayList<>();

    public MyRecyclerViewAdapter(AbstractFragmentGrid<T, S> abstractFragmentGrid) {
        this.abstractFragmentGrid = abstractFragmentGrid;
    }

    public void setmValues(List<T> mValues) {
        this.mValues = mValues;
    }

    public void addValue(T value) {
        mValues.add(value);
    }


    @Override
    public S onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(abstractFragmentGrid.getItemViewId(), parent, false);
        return abstractFragmentGrid.createViewHolder(view);
    }

    @Override
    public void onBindViewHolder(S holder, int position) {
        abstractFragmentGrid.setListenerRow(holder, mValues.get(position));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public T getItemByPosition(int position) {
        return mValues.get(position);
    }

    public void updateValue(T item) {
        for (int i = 0; i < mValues.size(); ++i) {
            T listItem = mValues.get(i);
            if (listItem.getId() == item.getId()) {
                mValues.set(i, item);
            }
        }
    }

    public void delete(int id) {
        for (int i = 0; i < mValues.size(); ++i) {
            T listItem = mValues.get(i);
            if (listItem.getId() == id) {
                mValues.remove(i);
            }
        }
    }
}
