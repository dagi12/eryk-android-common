package pl.edu.amu.wmi.erykandroidcommon.recycler.editdelete

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import pl.edu.amu.wmi.erykandroidcommon.recycler.delete.DeleteViewAdapter

/**
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@247.codes> on 28.07.17.
 */
internal abstract class EditDeleteViewAdapter<T, S : EditDeleteItemViewHolder<T>> : DeleteViewAdapter<T, S>() {

    private val onEditClickSubject = PublishSubject.create<T>()

    val editClicks: Observable<T>
        get() = onEditClickSubject.share()

    //    @Override
    //    public RecyclerViewWrapper<S> onCreateViewHolder(ViewGroup parent, int viewType) {
    //        RecyclerViewWrapper<S> wrapper = super.onCreateViewHolder(parent, viewType);
    //        S view = wrapper.getView();
    //        view.getEditClicks().subscribe(onEditClickSubject::onNext);
    //        return wrapper;
    //    }

    fun add(item: T) {
        items.add(item)
        notifyDataSetChanged()
    }
}
