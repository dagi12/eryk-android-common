package pl.edu.amu.wmi.erykandroidcommon.base;

import android.app.Fragment;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import io.reactivex.CompletableTransformer;
import io.reactivex.MaybeTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.SingleTransformer;
import pl.edu.amu.wmi.erykandroidcommon.exception.AdapterLackException;

/**
 * @author Eryk Mariankowski <eryk.mariankowski@softra.pl> on 18.10.17.
 */
public class BaseFragment extends Fragment {

    protected BaseAdapter baseAdapter;

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);

        try {
            baseAdapter = (BaseAdapter) context;
        } catch (ClassCastException e) {
            throw new AdapterLackException(context, BaseAdapter.class);
        }
    }

    @Nullable
    public String title() {
        return null;
    }

    public <T> SingleTransformer<T, T> singleWithThrobber() {
        return single -> single.doOnSubscribe(
                disposable -> baseAdapter.showThrobber(null))
                .doFinally(() -> baseAdapter.hideThrobber());
    }

    public <T> SingleTransformer<T, T> singleWithThrobber(@StringRes int throbberMessageResource,
                                                          @Nullable Object... formatArgs) {
        return single -> single.doOnSubscribe(
                disposable -> baseAdapter.showThrobber(throbberMessageResource,
                        formatArgs)).doFinally(() -> baseAdapter.hideThrobber());
    }

    public <T> ObservableTransformer<T, T> observableWithThrobber(
            @StringRes int throbberMessageResource, @Nullable Object... formatArgs) {
        return observable -> observable.doOnSubscribe(
                disposable -> baseAdapter.showThrobber(throbberMessageResource,
                        formatArgs)).doFinally(() -> baseAdapter.hideThrobber());
    }

    public <T> MaybeTransformer<T, T> maybeWithThrobber(@StringRes int throbberMessageResource,
                                                        @Nullable Object... formatArgs) {
        return maybe -> maybe.doOnSubscribe(
                disposable -> baseAdapter.showThrobber(throbberMessageResource,
                        formatArgs)).doFinally(() -> baseAdapter.hideThrobber());
    }

    public CompletableTransformer completableWithThrobber(@StringRes int throbberMessageResource,
                                                          @Nullable Object... formatArgs) {
        return completable -> completable.doOnSubscribe(
                disposable -> baseAdapter.showThrobber(throbberMessageResource,
                        formatArgs)).doFinally(() -> baseAdapter.hideThrobber());
    }
}
