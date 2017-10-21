package pl.edu.amu.wmi.erykandroidcommon.recycler.basic

import android.support.v7.widget.RecyclerView

import lombok.NonNull
import pl.edu.amu.wmi.erykandroidcommon.recycler.AbstractViewHolder
import pl.edu.amu.wmi.erykandroidcommon.util.ListUtils

abstract class BasicViewAdapter<T, S : AbstractViewHolder<T>> : RecyclerView.Adapter<*>() {

    protected var items: List<T>

    override fun getItemCount(): Int {
        return if (!ListUtils.isEmpty(items)) {
            items.size
        } else 0
    }

    fun setData(@NonNull items: List<T>) {
        this.items = items
        notifyDataSetChanged()
    }

}
