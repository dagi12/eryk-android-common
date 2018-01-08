package pl.edu.amu.wmi.erykandroidcommon.recycler.add

import pl.edu.amu.wmi.erykandroidcommon.recycler.basic.BasicRecyclerFragment

/**
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@247.codes> on 28.07.17.
 */
abstract class AddRecyclerFragment<T, S : AddItemViewHolder<T>> : BasicRecyclerFragment<T, S>() {

    @Suppress("UNCHECKED_CAST")
    public override fun initAdapter() {
        if (adapter == null) {
            adapter = adapterInit()
        }
        val addAdapter = adapter as AddViewAdapter<T, S>
        addAdapter.addClicks.subscribe({ this.add(it) })
    }

    protected abstract fun add(item: T)
}
