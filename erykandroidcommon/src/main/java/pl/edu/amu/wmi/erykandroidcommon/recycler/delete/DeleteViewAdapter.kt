package pl.edu.amu.wmi.erykandroidcommon.recycler.delete

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import lombok.NonNull
import pl.edu.amu.wmi.erykandroidcommon.recycler.basic.BasicViewAdapter

/**
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@247.codes> on 28.07.17.
 */
abstract class DeleteViewAdapter<T, S : DeleteItemViewHolder<T>> : BasicViewAdapter<T, S>() {

    private val onDeleteClickSubject = PublishSubject.create<T>()

    internal val deleteClicks: Observable<T>
        get() = onDeleteClickSubject.share()

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

    fun delete(@NonNull user: T) {
        items.remove(user)
        notifyDataSetChanged()
    }

}
