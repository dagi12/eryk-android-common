package pl.edu.amu.wmi.erykandroidcommon.recycler.editdelete;

import android.view.View
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import io.reactivex.subjects.PublishSubject
import pl.edu.amu.wmi.erykandroidcommon.R2

/**
 * @author Eryk Mariankowski <eryk.mariankowski@247.codes> on 28.07.17.
 */
public abstract class EditDeleteItemViewHolder<T> extends DeleteItemViewHolder<T> {

    @BindView(R2.id.btn_edit)
    protected ImageButton editButton;

    private final PublishSubject<T> onEditClickSubject = PublishSubject.create();

    public EditDeleteItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @OnClick(R2.id.btn_edit)
    protected void onEditClicked() {
        onEditClickSubject.onNext(item);
    }

    public Observable<T> getEditClicks() {
        return onEditClickSubject.share();
    }

}
