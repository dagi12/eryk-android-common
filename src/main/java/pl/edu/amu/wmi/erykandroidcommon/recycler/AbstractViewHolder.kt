package pl.edu.amu.wmi.erykandroidcommon.recycler

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class AbstractViewHolder<T : Any> protected constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
    lateinit var item: T
    abstract fun setRow()
}

fun concat(leftStr: String, rightStr: String?): String = leftStr + " " + (rightStr ?: "")