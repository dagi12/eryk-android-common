package pl.edu.amu.wmi.erykandroidcommon.recycler.editdelete

import android.view.View
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

import pl.edu.amu.wmi.erykandroidcommon.recycler.delete.DeleteItemViewHolder

/**
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@247.codes> on 28.07.17.
 */
abstract class EditDeleteItemViewHolder<T>(itemView: View) : DeleteItemViewHolder<T>(itemView) {

    private val onEditClickSubject = PublishSubject.create<T>()

    private val dupa: View.OnClickListener = View.OnClickListener {
        onEditClickSubject.onNext(item!!)
    }

    val editClicks: Observable<T>
        get() = onEditClickSubject.share()

    init {
//        btn_edit!!.setOnClickListener(dupa)
    }
}
