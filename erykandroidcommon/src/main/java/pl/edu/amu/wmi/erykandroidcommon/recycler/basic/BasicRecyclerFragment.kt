package pl.edu.amu.wmi.erykandroidcommon.recycler.basic

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.RecyclerView

import butterknife.BindView
import io.reactivex.Single
import lombok.NonNull
import pl.edu.amu.wmi.erykandroidcommon.R2
import pl.edu.amu.wmi.erykandroidcommon.base.BaseFragment
import pl.edu.amu.wmi.erykandroidcommon.recycler.AbstractViewHolder

abstract class BasicRecyclerFragment<T, S : AbstractViewHolder<T>> : BaseFragment() {

    @BindView(R2.id.recycler_view)
    var recyclerView: RecyclerView? = null

    protected var adapter: BasicViewAdapter<T, *>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initAdapter()
    }

    protected abstract fun initAdapter()

    abstract fun initUi()

    protected fun initBasicFragment(@NonNull itemsStream: Single<List<T>>) {
        if (adapter == null) {
            adapter = getAdapter()
        }
        recyclerView!!.adapter = adapter
        recyclerView!!.setHasFixedSize(true)
        recyclerView!!.addItemDecoration(
                DividerItemDecoration(recyclerView!!.context, RecyclerView.VERTICAL)
        )
        itemsStream.subscribe { items -> adapter!!.setData(items) }
    }

    protected abstract fun getAdapter(): BasicViewAdapter<T, S>

}
