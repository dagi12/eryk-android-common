package pl.edu.amu.wmi.erykandroidcommon.recycler.basic

import android.support.annotation.NonNull
import android.support.v7.widget.RecyclerView
import pl.edu.amu.wmi.erykandroidcommon.recycler.AbstractViewHolder
import pl.edu.amu.wmi.erykandroidcommon.util.ListUtils

abstract class BasicViewAdapter<T, S : AbstractViewHolder<T>> : RecyclerView.Adapter<S>() {

    protected var items: MutableList<T> = ArrayList()

    override fun getItemCount(): Int = if (!ListUtils.isEmpty(items)) {
        items.size
    } else 0

    fun setData(@NonNull items: List<T>) {
        this.items = items.toMutableList()
        notifyDataSetChanged()
    }

}
