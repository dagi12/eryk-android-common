package pl.edu.amu.wmi.erykandroidcommon.rx

import org.reactivestreams.Publisher

import java.util.concurrent.TimeUnit

import io.reactivex.Flowable
import io.reactivex.functions.Function
import lombok.NonNull
import timber.log.Timber

internal class RetryWithDelay : Function<Flowable<Throwable>, Publisher<*>> {
    private val maxRetries: Int

    private val retryDelaySeconds: Int

    private var retryCount: Int = 0


    private var autoUnlock = false

    constructor(maxRetries: Int, retryDelaySeconds: Int) {
        this.maxRetries = maxRetries
        this.retryDelaySeconds = retryDelaySeconds
        this.retryCount = 0
        this.autoUnlock = true
    }

    constructor(maxRetries: Int, retryDelaySeconds: Int, autoUnlock: Boolean) {
        this.maxRetries = maxRetries
        this.retryDelaySeconds = retryDelaySeconds
        this.retryCount = 0
        this.autoUnlock = autoUnlock
    }

    @Throws(Exception::class)
    override fun apply(@NonNull throwableFlowable: Flowable<Throwable>): Publisher<*> {
        return if (!autoUnlock) {
            throwableFlowable.flatMap(Function<Throwable, Publisher<*>> { Flowable.error(it) })
        } else throwableFlowable.flatMap { throwable ->
            if (++retryCount < maxRetries) {
                Timber.w(throwable, "Retry count: %d, max retries: %d", retryCount, maxRetries)
                return@throwableFlowable.flatMap Flowable . timer retryDelaySeconds.toLong(), TimeUnit.SECONDS)
            }

            // Max retries hit. Just pass the error along.
            Flowable.error<Long>(throwable)
        }
    }
}