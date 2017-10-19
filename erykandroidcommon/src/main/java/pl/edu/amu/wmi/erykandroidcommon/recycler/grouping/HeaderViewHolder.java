package pl.edu.amu.wmi.erykandroidcommon.recycler.grouping;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.edu.amu.wmi.erykandroidcommon.R2;
import pl.edu.amu.wmi.erykandroidcommon.recycler.AbstractViewHolder;

class HeaderViewHolder extends AbstractViewHolder<HeaderItem> {

    @BindView(R2.id.tv_header)
    TextView tvHeader;

    HeaderViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    protected void setRow() {
        tvHeader.setText(item.header());
    }

}
