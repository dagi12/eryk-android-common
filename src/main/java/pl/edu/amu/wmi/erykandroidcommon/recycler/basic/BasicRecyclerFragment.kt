package pl.edu.amu.wmi.erykandroidcommon.recycler.basic

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.RecyclerView
import io.reactivex.Single
import kotlinx.android.synthetic.main.stub.*
import pl.edu.amu.wmi.erykandroidcommon.base.BaseFragment
import pl.edu.amu.wmi.erykandroidcommon.recycler.AbstractViewHolder

abstract class BasicRecyclerFragment<T : Any, S : AbstractViewHolder<T>> : BaseFragment() {

    protected val adapter: BasicViewAdapter<T, *> by lazy {
        adapterInit()
    }

    protected abstract fun adapterInit(): BasicViewAdapter<T, S>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initAdapter()
    }

    protected abstract fun initAdapter()

    abstract fun initUi()

    protected fun initBasicFragment(itemsStream: Single<List<T>>) {
        recycler_view.adapter = adapter
        recycler_view.setHasFixedSize(true)
        recycler_view.addItemDecoration(
                DividerItemDecoration(recycler_view.context, RecyclerView.VERTICAL)
        )
        itemsStream.subscribe { items -> adapter.setData(items) }
    }
}
