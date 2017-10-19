package pl.edu.amu.wmi.erykandroidcommon.di;

import javax.annotation.Nullable;
import javax.inject.Inject;

import pl.edu.amu.wmi.erykandroidcommon.service.UserService;
import retrofit2.Retrofit;

public abstract class AbstractDaggerServiceTest {

    @Inject
    public UserService userService;

    @Inject
    public Retrofit retrofit;

    protected void setUp() {
//        JUnitTestComponent component = DaggerJUnitTestComponent.builder().jUnitTestModule(new JUnitTestModule()).build();
//        component.inject(this);
    }

    protected boolean isEmpty(@Nullable String str) {
        return str == null || str.length() == 0;
    }

}
