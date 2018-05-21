package pl.edu.amu.wmi.erykandroidcommon.recycler

import android.app.Fragment
import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pl.edu.amu.wmi.erykandroidcommon.exception.WrongViewException
import pl.edu.amu.wmi.erykandroidcommon.service.PicassoCache
import javax.inject.Inject

abstract class AbstractFragmentGrid<T : UniqueItem, S : AbstractViewHolder<T>> : Fragment() {

    @Inject
    lateinit var picassoCache: PicassoCache
    @Deprecated("Use buttery progress bar instead")
    lateinit var progressDialog: ProgressDialog

    private var mListener: InteractionListener<T>? = null

    lateinit var myRecyclerViewAdapter: MyRecyclerViewAdapter<T, S>

    protected abstract val listWrapperId: Int

    abstract val itemViewId: Int

    protected open fun initRecyclerView(recyclerView: RecyclerView) {
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        myRecyclerViewAdapter = MyRecyclerViewAdapter(this)
        recyclerView.adapter = myRecyclerViewAdapter
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(listWrapperId, container, false)
        var recyclerViewId = 0
        if (arguments != null) {
            recyclerViewId = arguments.getInt(RECYCLER_VIEW_ID_PARAM)
        }
        if (recyclerViewId == 0) {
            if (view is RecyclerView) {
                initRecyclerView(view)
            } else {
                throw WrongViewException()
            }
        } else {
            initRecyclerView(view.findViewById(recyclerViewId))
        }
        return view
    }

    fun setData(items: MutableList<T>) {
        myRecyclerViewAdapter.setValues(items)
        myRecyclerViewAdapter.notifyDataSetChanged()
    }

    abstract fun createViewHolder(view: View): S

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val context = activity
        progressDialog = ProgressDialog(context)
        if (context is InteractionListener<*>) {
            mListener = context as InteractionListener<T>
        }
    }

    fun setListenerRow(holder: S, t: T) {
        holder.item = t
        holder.setRow()
        holder.view.setOnClickListener {
            mListener?.action(holder.item)
        }
    }

    fun addData(items: T) {
        myRecyclerViewAdapter.addValue(items)
        myRecyclerViewAdapter.notifyDataSetChanged()
    }

    fun update(postRequestResult: T) {
        myRecyclerViewAdapter.updateValue(postRequestResult)
        myRecyclerViewAdapter.notifyDataSetChanged()
    }

    fun delete(postId: Int) {
        myRecyclerViewAdapter.delete(postId)
        myRecyclerViewAdapter.notifyDataSetChanged()
    }

    companion object {
        const val RECYCLER_VIEW_ID_PARAM = "RECYCLER_VIEW_ID"
    }
}
