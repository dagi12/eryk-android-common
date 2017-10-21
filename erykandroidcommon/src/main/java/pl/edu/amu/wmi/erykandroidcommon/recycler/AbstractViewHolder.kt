package pl.edu.amu.wmi.erykandroidcommon.recycler

import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.View

import lombok.Getter
import lombok.Setter

abstract class AbstractViewHolder<T> protected constructor(val view: View) : RecyclerView.ViewHolder(view) {

    @Getter
    @Setter
    var item: T? = null

    abstract fun setRow()

    companion object {

        protected fun concat(leftStr: String, rightStr: String): String {
            return leftStr + " " + if (TextUtils.isEmpty(rightStr)) "" else rightStr
        }
    }

}
