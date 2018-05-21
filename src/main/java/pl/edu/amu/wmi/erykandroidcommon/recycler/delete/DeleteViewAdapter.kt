package pl.edu.amu.wmi.erykandroidcommon.recycler.delete

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import pl.edu.amu.wmi.erykandroidcommon.recycler.basic.BasicViewAdapter

/**
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@247.codes> on 28.07.17.
 */
abstract class DeleteViewAdapter<T : Any, S : DeleteItemViewHolder<T>> : BasicViewAdapter<T, S>() {

    private val onDeleteClickSubject = PublishSubject.create<T>()

    internal val deleteClicks: Observable<T>
        get() = onDeleteClickSubject.share()

    //    @Override
    //    public RecyclerViewWrapper<S> onCreateViewHolder(ViewGroup parent, int viewType) {
    //        RecyclerViewWrapper<S> wrapper = super.onCreateViewHolder(parent, viewType);
    //        S view = wrapper.getView();
    //        view.getDeleteClicks().subscribe(onDeleteClickSubject::onNext);
    //        return wrapper;
    //    }

    //    @Override
    //    protected void bindRow(final S view, final T user) {
    //        view.bind(user);
    //    }

    fun delete(user: T) {
        items.remove(user)
        notifyDataSetChanged()
    }
}
