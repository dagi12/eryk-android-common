package pl.edu.amu.wmi.erykandroidcommon.rx;

import org.reactivestreams.Publisher;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import lombok.NonNull;
import timber.log.Timber;

public class RetryWithDelay implements Function<Flowable<Throwable>, Publisher<?>> {
    private final int maxRetries;

    private final int retryDelaySeconds;

    private int retryCount;


    private boolean autoUnlock = false;

    public RetryWithDelay(final int maxRetries, final int retryDelaySeconds) {
        this.maxRetries = maxRetries;
        this.retryDelaySeconds = retryDelaySeconds;
        this.retryCount = 0;
        this.autoUnlock = true;
    }

    public RetryWithDelay(final int maxRetries, final int retryDelaySeconds, boolean autoUnlock) {
        this.maxRetries = maxRetries;
        this.retryDelaySeconds = retryDelaySeconds;
        this.retryCount = 0;
        this.autoUnlock = autoUnlock;
    }

    @Override
    public Publisher<?> apply(@NonNull Flowable<Throwable> throwableFlowable) throws Exception {
        if (!autoUnlock) {
            return throwableFlowable.flatMap(Flowable::error);
        }
        return throwableFlowable.flatMap(throwable -> {
            if (++retryCount < maxRetries) {
                Timber.w(throwable, "Retry count: %d, max retries: %d", retryCount, maxRetries);
                return Flowable.timer(retryDelaySeconds, TimeUnit.SECONDS);
            }

            // Max retries hit. Just pass the error along.
            return Flowable.error(throwable);
        });
    }
}