package pl.edu.amu.wmi.erykandroidcommon.recycler.grouping;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import pl.edu.amu.wmi.erykandroidcommon.base.BaseFragment;
import pl.edu.amu.wmi.erykandroidcommon.recycler.AbstractViewHolder;


public abstract class GroupedRecyclerFragment<T extends ListItem, P extends View, U extends AbstractViewHolder<P>> extends BaseFragment {

    protected void initRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        GroupedRecyclerAdapter<T, P, U> groupedRecyclerAdapter = instantiateAdapter();
        recyclerView.setAdapter(groupedRecyclerAdapter);
    }

    protected abstract GroupedRecyclerAdapter<T, P, U> instantiateAdapter();

}
