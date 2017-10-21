package pl.edu.amu.wmi.erykandroidcommon.recycler.editdelete

import android.view.View
import android.widget.ImageButton

import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import pl.edu.amu.wmi.erykandroidcommon.R2
import pl.edu.amu.wmi.erykandroidcommon.recycler.delete.DeleteItemViewHolder

/**
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@247.codes> on 28.07.17.
 */
abstract class EditDeleteItemViewHolder<T>(itemView: View) : DeleteItemViewHolder<T>(itemView) {

    @BindView(R2.id.btn_edit)
    var editButton: ImageButton? = null

    private val onEditClickSubject = PublishSubject.create<T>()

    val editClicks: Observable<T>
        get() = onEditClickSubject.share()

    init {
        ButterKnife.bind(this, itemView)
    }

    @OnClick(R2.id.btn_edit)
    fun onEditClicked() {
        onEditClickSubject.onNext(item)
    }

}
