package pl.edu.amu.wmi.erykandroidcommon.di

import javax.inject.Inject

import pl.edu.amu.wmi.erykandroidcommon.service.UserService
import retrofit2.Retrofit

abstract class AbstractDaggerServiceTest {

    @Inject
    var userService: UserService<*, *>? = null

    @Inject
    var retrofit: Retrofit? = null

    protected fun setUp() {
        //        JUnitTestComponent component = DaggerJUnitTestComponent.builder().jUnitTestModule(new JUnitTestModule()).build();
        //        component.inject(this);
    }

    protected fun isEmpty(str: String?): Boolean {
        return str == null || str.length == 0
    }

}
