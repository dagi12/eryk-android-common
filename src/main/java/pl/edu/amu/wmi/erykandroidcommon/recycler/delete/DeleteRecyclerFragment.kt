package pl.edu.amu.wmi.erykandroidcommon.recycler.delete

import io.reactivex.Observable
import pl.edu.amu.wmi.erykandroidcommon.recycler.basic.BasicRecyclerFragment

/**
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@247.codes> on 28.07.17.
 */
abstract class DeleteRecyclerFragment<T : Any, S : DeleteItemViewHolder<T>> : BasicRecyclerFragment<T, S>() {

    @Suppress("UNCHECKED_CAST")
    override fun initBaseAdapter() {
        val deleteAdapter = adapter as DeleteViewAdapter<T, S>
        val clicks: Observable<T> = deleteAdapter.deleteClicks
        clicks.subscribe(::delete)
    }

    protected abstract fun delete(item: T)
}
