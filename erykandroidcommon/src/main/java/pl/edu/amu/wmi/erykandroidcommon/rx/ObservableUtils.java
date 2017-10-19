package pl.edu.amu.wmi.erykandroidcommon.rx;

import io.reactivex.CompletableTransformer;
import io.reactivex.MaybeTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Goodies and cookies for using reactive streams.
 *
 * @author Eryk Mariankowski <eryk.mariankowski@softra.pl> on 09.06.2017
 */
class ObservableUtils {

    private static final ObservableTransformer observableSchedulersTransformer
            = observable -> observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());

    private static final SingleTransformer singleSchedulersTransformer
            = single -> single.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());

    private static final MaybeTransformer maybeSchedulersTransformer = maybe -> maybe.subscribeOn(
            Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

    private ObservableUtils() {

    }

    @SuppressWarnings("unchecked")
    public static <T> ObservableTransformer<T, T> backgroundObservableSchedulers() {
        return (ObservableTransformer<T, T>) observableSchedulersTransformer;
    }

    @SuppressWarnings("unchecked")
    public static <T> SingleTransformer<T, T> backgroundSingleSchedulers() {
        return (SingleTransformer<T, T>) singleSchedulersTransformer;
    }

    @SuppressWarnings("unchecked")
    public static <T> MaybeTransformer<T, T> backgroundMaybeSchedulers() {
        return (MaybeTransformer<T, T>) maybeSchedulersTransformer;
    }

    @SuppressWarnings("unchecked")
    public static CompletableTransformer backgroundCompletableSchedulers() {
        return completable -> completable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
