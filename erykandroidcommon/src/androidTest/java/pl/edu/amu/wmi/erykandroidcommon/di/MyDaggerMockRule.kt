package pl.edu.amu.wmi.erykandroidcommon.di;

import android.app.Application;
import android.support.test.InstrumentationRegistry;

import it.cosenonjaviste.daggermock.DaggerMockRule;

public class MyDaggerMockRule extends DaggerMockRule<TestComponent> {

    public MyDaggerMockRule() {
        super(TestComponent.class, new TestModule(getApp()));
        set(new ComponentSetter<TestComponent>() {
            @Override
            public void setComponent(TestComponent component) {
//                getApp().setComponent(component);
            }
        });
    }

    private static Application getApp() {
        return (Application) InstrumentationRegistry.getInstrumentation()
                .getTargetContext().getApplicationContext();
    }

}
