package pl.edu.amu.wmi.erykandroidcommon.recycler.add

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import pl.edu.amu.wmi.erykandroidcommon.recycler.basic.BasicViewAdapter

/**
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@247.codes> on 28.07.17.
 */
internal abstract class AddViewAdapter<T, S : AddItemViewHolder<T>> : BasicViewAdapter<T, S>() {

    private val onAddClickSubject = PublishSubject.create<T>()

    val addClicks: Observable<T>
        get() = onAddClickSubject.share()

    //    @Override
    //    public RecyclerViewWrapper<S> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    //        RecyclerViewWrapper<S> wrapper = super.onCreateViewHolder(parent, viewType);
    //        S view = wrapper.getView();
    //        view.getAddClicks().subscribe(onAddClickSubject::onNext);
    //        return wrapper;
    //    }

    //    @Override
    //    protected void bindRow(@NonNull final S view, @NonNull final T user) {
    //        view.bind(user);
    //    }

}
