package pl.edu.amu.wmi.erykandroidcommon.recycler.select

import android.view.View
import android.widget.RadioButton
import pl.edu.amu.wmi.erykandroidcommon.recycler.AbstractViewHolder

/**
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@247.codes> on 31.08.17.
 */
abstract class SelectItemViewHolder<T>(itemView: View) : AbstractViewHolder<T>(itemView) {

    var radio: RadioButton? = null

    init {
//        radio = itemView.radio
    }

    abstract fun bind(item: T)
}
