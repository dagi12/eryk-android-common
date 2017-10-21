package pl.edu.amu.wmi.erykandroidcommon.recycler.basic;

import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v7.widget.RecyclerView
import butterknife.BindView
import lombok.NonNull
import pl.edu.amu.wmi.erykandroidcommon.R2

public abstract class BasicRecyclerFragment<T, S extends AbstractViewHolder<T>> extends BaseFragment {

    @BindView(R2.id.recycler_view)
    protected RecyclerView recyclerView;

    protected BasicViewAdapter<T, ?> adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initAdapter();
    }

    protected abstract void initAdapter();

    public abstract void initUi();

    protected void initBasicFragment(@NonNull final Single<List<T>> itemsStream) {
        if (adapter == null) {
            adapter = getAdapter();
        }
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(
                new DividerItemDecoration(recyclerView.getContext(), RecyclerView.VERTICAL)
        );
        itemsStream.subscribe(items -> adapter.setData(items));
    }

    protected abstract BasicViewAdapter<T, S> getAdapter();

}
