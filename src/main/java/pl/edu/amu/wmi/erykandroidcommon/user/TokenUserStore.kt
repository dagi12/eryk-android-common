package pl.edu.amu.wmi.erykandroidcommon.user

import pl.edu.amu.wmi.erykandroidcommon.di.CommonApplication
import pl.edu.amu.wmi.erykandroidcommon.service.Token

/**
 * @author Eryk Mariankowski <eryk.mariankowski@247.codes> on 15.01.18.
 */
abstract class TokenUserStore<T : Token>(application: CommonApplication<*>) : UserStore<T>(application)