package pl.edu.amu.wmi.erykandroidcommon.recycler.select

import android.content.Context
import pl.edu.amu.wmi.erykandroidcommon.exception.NotImplementedException
import pl.edu.amu.wmi.erykandroidcommon.recycler.basic.BasicRecyclerFragment

/**
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@247.codes> on 31.08.17.
 */
abstract class SelectRecyclerFragment<T : Any, S : SelectItemViewHolder<T>> : BasicRecyclerFragment<T, S>() {

    private val selectAdapter: SelectViewAdapter<T, S>? = null

    private lateinit var addClickedListener: OnAddClickedListener<T>

    fun onButtonAddClick() = addClickedListener.onAddClicked(selectAdapter!!.getSelectedItem())

    @Suppress("UNCHECKED_CAST")
    override fun onAttach(context: Context) {
        super.onAttach(context)
        addClickedListener = try {
            context as OnAddClickedListener<T>
        } catch (e: ClassCastException) {
            throw NotImplementedException(context, OnAddClickedListener::class.java)
        }
    }

    interface OnAddClickedListener<in T> {
        fun onAddClicked(item: T?)
    }
}
