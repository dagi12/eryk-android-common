package pl.edu.amu.wmi.erykandroidcommon.recycler.grouping

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pl.edu.amu.wmi.erykandroidcommon.R
import pl.edu.amu.wmi.erykandroidcommon.recycler.AbstractViewHolder
import java.util.ArrayList

abstract class GroupedRecyclerAdapter<in T : ListItem, P : View, in U : AbstractViewHolder<P>> : RecyclerView.Adapter<AbstractViewHolder<*>>() {

    private var mItems: List<ListItem> = ArrayList()

    fun setData(items: List<ListItem>) {
        this.mItems = items
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = mItems.size

    override fun getItemViewType(position: Int): Int = mItems[position].type.type

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbstractViewHolder<*> =
        if (viewType == ListItemType.TYPE_HEADER.type) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_grouping_header, parent, false)
            HeaderViewHolder(view)
        } else {
            createMyViewHolder(parent)
        }

    protected abstract fun createMyViewHolder(parent: ViewGroup): AbstractViewHolder<*>

    protected abstract fun bindViewHolder(viewHolder: U, t: T)

//    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
//        val item = mItems[position]
//        if (ListItemType.TYPE_HEADER.type() == item.type.type()) {
//            val holder = viewHolder as HeaderViewHolder
//            holder.setRow()
//        } else {
//            bindViewHolder(viewHolder as U, item as T)
//        }
//    }

}
