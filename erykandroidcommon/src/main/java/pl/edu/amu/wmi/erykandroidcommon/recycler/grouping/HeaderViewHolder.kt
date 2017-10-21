package pl.edu.amu.wmi.erykandroidcommon.recycler.grouping

import android.view.View
import android.widget.TextView

import butterknife.BindView
import butterknife.ButterKnife
import pl.edu.amu.wmi.erykandroidcommon.R2
import pl.edu.amu.wmi.erykandroidcommon.recycler.AbstractViewHolder

internal class HeaderViewHolder(itemView: View) : AbstractViewHolder<HeaderItem>(itemView) {

    @BindView(R2.id.tv_header)
    var tvHeader: TextView? = null

    init {
        ButterKnife.bind(this, itemView)
    }

    public override fun setRow() {
        tvHeader!!.text = item!!.header()
    }

}
