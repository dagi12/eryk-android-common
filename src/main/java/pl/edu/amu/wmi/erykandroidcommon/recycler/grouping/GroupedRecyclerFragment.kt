package pl.edu.amu.wmi.erykandroidcommon.recycler.grouping

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

import pl.edu.amu.wmi.erykandroidcommon.base.BaseFragment
import pl.edu.amu.wmi.erykandroidcommon.recycler.AbstractViewHolder

abstract class GroupedRecyclerFragment<in T : ListItem, P : View, in U : AbstractViewHolder<P>> : BaseFragment() {

    protected fun initRecyclerView(recyclerView: RecyclerView) {
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        val groupedRecyclerAdapter = instantiateAdapter()
        recyclerView.adapter = groupedRecyclerAdapter
    }

    protected abstract fun instantiateAdapter(): GroupedRecyclerAdapter<T, P, U>
}
