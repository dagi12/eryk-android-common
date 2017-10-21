package pl.edu.amu.wmi.erykandroidcommon.di;

import android.util.Log
import com.orhanobut.logger.Logger
import io.fabric.sdk.android.Fabric
import io.reactivex.plugins.RxJavaPlugins
import lombok.Getter
import timber.log.Timber

/**
 * @author Eryk Mariankowski <eryk.mariankowski@247.codes> on 21.10.17.
 */
public abstract class CommonApplication extends MultiDexApplication {

    private static final String UNHANDLED_EXCEPTION_MESSAGE = "Unhandled exception";

    @Getter
    private CommonApplicationComponent commonApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initLogging();
        RxJavaPlugins.setErrorHandler(throwable -> Timber.e(throwable, UNHANDLED_EXCEPTION_MESSAGE));
        commonApplicationComponent = DaggerCommonApplicationComponent
                .builder()
                .commonApplicationModule(new CommonApplicationModule(getApplicationContext()))
                .build();
    }

    private void initLogging() {
        Fabric.with(this, new Crashlytics());
        Logger.addLogAdapter(new AndroidLogAdapter());
        Timber.plant(new Timber.DebugTree() {
            @Override
            protected void log(int priority, String tag, String message, Throwable t) {
                Logger.log(priority, tag, message, t);
            }
        });
        Timber.plant(new CrashlyticsLogTree(Log.DEBUG));
        Timber.plant(new CrashlyticsLogExceptionTree());
    }
}
