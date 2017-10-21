package pl.edu.amu.wmi.erykandroidcommon.recycler.delete

import android.view.View
import android.widget.ImageButton

import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import pl.edu.amu.wmi.erykandroidcommon.R2
import pl.edu.amu.wmi.erykandroidcommon.recycler.AbstractViewHolder

/**
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@247.codes> on 28.07.17.
 */
abstract class DeleteItemViewHolder<T> protected constructor(itemView: View) : AbstractViewHolder<T>(itemView) {

    @BindView(R2.id.btn_delete)
    var deleteButton: ImageButton? = null

    private val onDeleteClickSubject = PublishSubject.create<T>()

    val deleteClicks: Observable<T>
        get() = onDeleteClickSubject.share()

    init {
        ButterKnife.bind(this, itemView)
    }

    @OnClick(R2.id.btn_delete)
    fun onDeleteClicked() {
        onDeleteClickSubject.onNext(item)
    }

    fun deleteVisible(isVisible: Boolean) {
        deleteButton!!.visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
    }

}
