package pl.edu.amu.wmi.erykandroidcommon.recycler.select;

import android.view.View;
import android.widget.RadioButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.Getter;
import pl.edu.amu.wmi.erykandroidcommon.R2;
import pl.edu.amu.wmi.erykandroidcommon.recycler.AbstractViewHolder;

/**
 * @author Eryk Mariankowski <eryk.mariankowski@247.codes> on 31.08.17.
 */
public abstract class SelectItemViewHolder<T> extends AbstractViewHolder<T> {

    @BindView(R2.id.radio)
    @Getter
    public RadioButton radio;

    public SelectItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public abstract void bind(T item);
}
