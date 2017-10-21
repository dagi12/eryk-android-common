package pl.edu.amu.wmi.erykandroidcommon.recycler.delete

import lombok.NonNull
import pl.edu.amu.wmi.erykandroidcommon.recycler.basic.BasicRecyclerFragment

/**
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@247.codes> on 28.07.17.
 */
abstract class DeleteRecyclerFragment<T, S : DeleteItemViewHolder<T>> : BasicRecyclerFragment<T, S>() {

    override fun initAdapter() {
        if (adapter == null) {
            adapter = getAdapter()
        }
        val deleteAdapter = adapter as DeleteViewAdapter<*, *>
        deleteAdapter.deleteClicks.subscribe(Consumer<T> { this.delete(it) })
    }

    protected abstract fun delete(@NonNull item: T)

}
