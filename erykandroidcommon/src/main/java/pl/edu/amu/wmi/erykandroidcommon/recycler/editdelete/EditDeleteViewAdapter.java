package pl.edu.amu.wmi.erykandroidcommon.recycler.editdelete;

import android.view.ViewGroup;




import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import lombok.NonNull;
import pl.edu.amu.wmi.erykandroidcommon.recycler.delete.DeleteViewAdapter;

/**
 * @author Eryk Mariankowski <eryk.mariankowski@247.codes> on 28.07.17.
 */
public abstract class EditDeleteViewAdapter<T, S extends EditDeleteItemViewHolder<T>> extends DeleteViewAdapter<T, S> {

    private final PublishSubject<T> onEditClickSubject = PublishSubject.create();

    Observable<T> getEditClicks() {
        return onEditClickSubject.share();
    }

//    @Override
//    public RecyclerViewWrapper<S> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        RecyclerViewWrapper<S> wrapper = super.onCreateViewHolder(parent, viewType);
//        S view = wrapper.getView();
//        view.getEditClicks().subscribe(onEditClickSubject::onNext);
//        return wrapper;
//    }

    public void add(@NonNull final T item) {
        items.add(item);
        notifyDataSetChanged();
    }

}
