package pl.edu.amu.wmi.erykandroidcommon.recycler.add

import android.view.View

import butterknife.OnClick
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import pl.edu.amu.wmi.erykandroidcommon.R2
import pl.edu.amu.wmi.erykandroidcommon.recycler.AbstractViewHolder

/**
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@247.codes> on 28.07.17.
 */
abstract class AddItemViewHolder<T>(itemView: View) : AbstractViewHolder<T>(itemView) {

    private val onAddClickSubject = PublishSubject.create<T>()

    val addClicks: Observable<T>
        get() = onAddClickSubject.share()

    @OnClick(R2.id.btn_add)
    fun onDeleteClicked() {
        onAddClickSubject.onNext(item)
    }

}
