package pl.edu.amu.wmi.erykandroidcommon.di;

import android.support.multidex.MultiDexApplication;

import lombok.Getter;

/**
 * @author Eryk Mariankowski <eryk.mariankowski@247.codes> on 21.10.17.
 */
public abstract class CommonApplication extends MultiDexApplication {

    @Getter
    private CommonApplicationComponent commonApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        commonApplicationComponent = DaggerCommonApplicationComponent
                .builder()
                .commonApplicationModule(new CommonApplicationModule(getApplicationContext()))
                .build();
    }
}
