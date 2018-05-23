package pl.edu.amu.wmi.erykandroidcommon.service

import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@247.codes> on 23.05.18.
 */
class UnauthorizedInterceptor : Interceptor {

    lateinit var errorHandler: Runnable

    override fun intercept(chain: Interceptor.Chain): Response {
        val res = chain.proceed(chain.request())
        if (res.code() == 401) {
            errorHandler.run()
        }
        return res
    }

}