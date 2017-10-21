package pl.edu.amu.wmi.erykandroidcommon.recycler.delete;

import android.view.View;
import android.widget.ImageButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import pl.edu.amu.wmi.erykandroidcommon.R2;
import pl.edu.amu.wmi.erykandroidcommon.recycler.AbstractViewHolder;

/**
 * @author Eryk Mariankowski <eryk.mariankowski@247.codes> on 28.07.17.
 */
public abstract class DeleteItemViewHolder<T> extends AbstractViewHolder<T> {

    @BindView(R2.id.btn_delete)
    protected ImageButton deleteButton;

    private final PublishSubject<T> onDeleteClickSubject = PublishSubject.create();

    protected DeleteItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @OnClick(R2.id.btn_delete)
    protected void onDeleteClicked() {
        onDeleteClickSubject.onNext(item);
    }

    public Observable<T> getDeleteClicks() {
        return onDeleteClickSubject.share();
    }

    public void deleteVisible(final boolean isVisible) {
        deleteButton.setVisibility(isVisible ? View.VISIBLE : View.INVISIBLE);
    }

}
