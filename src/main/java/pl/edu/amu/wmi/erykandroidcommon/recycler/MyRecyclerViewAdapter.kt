package pl.edu.amu.wmi.erykandroidcommon.recycler

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import java.util.ArrayList

public class MyRecyclerViewAdapter<T : UniqueItem, S : AbstractViewHolder<T>>(private val abstractFragmentGrid: AbstractFragmentGrid<T, S>) : RecyclerView.Adapter<S>() {

    private var values: MutableList<T> = ArrayList()

    fun setValues(values: MutableList<T>) {
        this.values = values
    }

    fun addValue(value: T) {
        values.add(value)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): S {
        val view = LayoutInflater.from(parent.context).inflate(abstractFragmentGrid.itemViewId, parent, false)
        return abstractFragmentGrid.createViewHolder(view)
    }

    override fun onBindViewHolder(holder: S, position: Int) {
        abstractFragmentGrid.setListenerRow(holder, values[position])
    }

    override fun getItemCount(): Int = values.size

    fun getItemByPosition(position: Int): T = values[position]

    fun updateValue(item: T) {
        for (i in values.indices) {
            val listItem = values[i]
            if (listItem.id == item.id) {
                values[i] = item
            }
        }
    }

    fun delete(id: Int) {
        for (i in values.indices) {
            val listItem = values[i]
            if (listItem.id == id) {
                values.removeAt(i)
            }
        }
    }
}
