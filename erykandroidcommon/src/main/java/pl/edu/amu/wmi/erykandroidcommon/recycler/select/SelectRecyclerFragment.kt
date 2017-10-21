package pl.edu.amu.wmi.erykandroidcommon.recycler.select

import android.content.Context
import pl.edu.amu.wmi.erykandroidcommon.exception.NotImplementedException
import pl.edu.amu.wmi.erykandroidcommon.recycler.basic.BasicRecyclerFragment

/**
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@247.codes> on 31.08.17.
 */
abstract class SelectRecyclerFragment<T, S : SelectItemViewHolder<T>> : BasicRecyclerFragment<T, S>() {


    private val selectAdapter: SelectViewAdapter<T, S>? = null

    private var addClickedListener: OnAddClickedListener<T>? = null

    fun onButtonAddClick() {
        addClickedListener!!.onAddClicked(selectAdapter!!.getSelectedItem())
    }

    @SuppressWarnings("unchecked")
    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            addClickedListener = context as OnAddClickedListener<T>
        } catch (e: ClassCastException) {
            throw NotImplementedException(context, OnAddClickedListener::class.java)
        }

    }

    interface OnAddClickedListener<T> {
        fun onAddClicked(item: T?)
    }

}
