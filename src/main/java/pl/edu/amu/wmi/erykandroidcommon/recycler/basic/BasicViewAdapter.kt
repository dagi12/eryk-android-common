package pl.edu.amu.wmi.erykandroidcommon.recycler.basic

import android.support.v7.widget.RecyclerView
import pl.edu.amu.wmi.erykandroidcommon.helper.ListUtils
import pl.edu.amu.wmi.erykandroidcommon.recycler.AbstractViewHolder

abstract class BasicViewAdapter<T : Any, S : AbstractViewHolder<T>> : RecyclerView.Adapter<S>() {

    protected var items: MutableList<T> = ArrayList()

    override fun getItemCount(): Int = if (!ListUtils.isEmpty(items)) {
        items.size
    } else 0

    fun setData(items: List<T>) {
        this.items = items.toMutableList()
        notifyDataSetChanged()
    }
}
