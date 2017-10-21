package pl.edu.amu.wmi.erykandroidcommon.recycler.select

import android.view.View
import android.widget.RadioButton

import butterknife.BindView
import butterknife.ButterKnife
import lombok.Getter
import pl.edu.amu.wmi.erykandroidcommon.R2
import pl.edu.amu.wmi.erykandroidcommon.recycler.AbstractViewHolder

/**
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@247.codes> on 31.08.17.
 */
abstract class SelectItemViewHolder<T>(itemView: View) : AbstractViewHolder<T>(itemView) {

    @BindView(R2.id.radio)
    @Getter
    var radio: RadioButton? = null

    init {
        ButterKnife.bind(this, itemView)
    }

    abstract fun bind(item: T)
}
