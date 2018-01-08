package pl.edu.amu.wmi.erykandroidcommon.recycler.delete

import android.view.View
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import pl.edu.amu.wmi.erykandroidcommon.recycler.AbstractViewHolder

/**
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@247.codes> on 28.07.17.
 */
abstract class DeleteItemViewHolder<T> protected constructor(itemView: View) : AbstractViewHolder<T>(itemView) {

    private val onDeleteClickSubject = PublishSubject.create<T>()

    val deleteClicks: Observable<T>
        get() = onDeleteClickSubject.share()

    init {
//        btn_delete.set
    }

    fun onDeleteClicked() = onDeleteClickSubject.onNext(item!!)

    fun deleteVisible(isVisible: Boolean) {
//        btn_delete!!.visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
    }
}
