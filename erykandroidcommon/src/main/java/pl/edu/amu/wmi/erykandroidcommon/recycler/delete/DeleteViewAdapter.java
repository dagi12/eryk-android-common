package pl.edu.amu.wmi.erykandroidcommon.recycler.delete;

import android.view.ViewGroup;




import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import lombok.NonNull;
import pl.edu.amu.wmi.erykandroidcommon.recycler.basic.BasicViewAdapter;

/**
 * @author Eryk Mariankowski <eryk.mariankowski@247.codes> on 28.07.17.
 */
public abstract class DeleteViewAdapter<T, S extends DeleteItemViewHolder<T>> extends BasicViewAdapter<T, S> {

    private final PublishSubject<T> onDeleteClickSubject = PublishSubject.create();

    Observable<T> getDeleteClicks() {
        return onDeleteClickSubject.share();
    }

//    @Override
//    public RecyclerViewWrapper<S> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        RecyclerViewWrapper<S> wrapper = super.onCreateViewHolder(parent, viewType);
//        S view = wrapper.getView();
//        view.getDeleteClicks().subscribe(onDeleteClickSubject::onNext);
//        return wrapper;
//    }

//    @Override
//    protected void bindRow(@NonNull final S view, @NonNull final T user) {
//        view.bind(user);
//    }

    public void delete(@NonNull final T user) {
        items.remove(user);
        notifyDataSetChanged();
    }

}
