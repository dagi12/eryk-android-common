package pl.edu.amu.wmi.erykandroidcommon.recycler;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import pl.edu.amu.wmi.erykandroidcommon.exception.WrongViewException;


public abstract class AbstractFragmentGrid<T extends UniqueItem, S extends AbstractViewHolder<T>> extends Fragment {

    private static final String RECYCLER_VIEW_ID_PARAM = "RECYCLER_VIEW_ID";

    private OnListFragmentInteractionListener<T> mListener;

    private MyRecyclerViewAdapter<T, S> myRecyclerViewAdapter;

    private void initRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        myRecyclerViewAdapter = new MyRecyclerViewAdapter<>(this);
        recyclerView.setAdapter(myRecyclerViewAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(getListWrapperId(), container, false);
        int recyclerViewId = 0;
        if (getArguments() != null) {
            recyclerViewId = getArguments().getInt(RECYCLER_VIEW_ID_PARAM);
        }
        if (recyclerViewId == 0) {
            if (view instanceof RecyclerView) {
                initRecyclerView((RecyclerView) view);
            } else {
                throw new WrongViewException();
            }
        } else {
            initRecyclerView(view.findViewById(recyclerViewId));
        }
        return view;
    }

    protected abstract int getListWrapperId();

    public void setData(List<T> items) {
        myRecyclerViewAdapter.setValues(items);
        myRecyclerViewAdapter.notifyDataSetChanged();
    }

    public abstract S createViewHolder(View view);

    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context context = getActivity();
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        }
    }

    public abstract int getItemViewId();

    public void setListenerRow(final S holder, T t) {
        holder.item = t;
        holder.setRow();
        holder.view.setOnClickListener(view -> {
            if (mListener != null) {
                mListener.onListFragmentInteraction(holder.item);
            }
        });

    }

    public void addData(T items) {
        myRecyclerViewAdapter.addValue(items);
        myRecyclerViewAdapter.notifyDataSetChanged();
    }

    public void update(T postRequestResult) {
        myRecyclerViewAdapter.updateValue(postRequestResult);
        myRecyclerViewAdapter.notifyDataSetChanged();
    }

    public void delete(int postId) {
        myRecyclerViewAdapter.delete(postId);
        myRecyclerViewAdapter.notifyDataSetChanged();
    }

}
