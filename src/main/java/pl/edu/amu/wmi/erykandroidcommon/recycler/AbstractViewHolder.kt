package pl.edu.amu.wmi.erykandroidcommon.recycler

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class AbstractViewHolder<T> protected constructor(val view: View) : RecyclerView.ViewHolder(view) {
    var item: T? = null
    abstract fun setRow()
}

fun concat(leftStr: String, rightStr: String?): String = leftStr + " " + (rightStr ?: "")