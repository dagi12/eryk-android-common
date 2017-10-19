package pl.edu.amu.wmi.erykandroidcommon.recycler.add;

import android.view.View;

import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import pl.edu.amu.wmi.erykandroidcommon.R2;
import pl.edu.amu.wmi.erykandroidcommon.recycler.AbstractViewHolder;

/**
 * @author Eryk Mariankowski <eryk.mariankowski@247.codes> on 28.07.17.
 */
public abstract class AddItemViewHolder<T> extends AbstractViewHolder<T> {

    private final PublishSubject<T> onAddClickSubject = PublishSubject.create();

    public AddItemViewHolder(View itemView) {
        super(itemView);
    }

    @OnClick(R2.id.btn_add)
    protected void onDeleteClicked() {
        onAddClickSubject.onNext(item);
    }

    public Observable<T> getAddClicks() {
        return onAddClickSubject.share();
    }

}
