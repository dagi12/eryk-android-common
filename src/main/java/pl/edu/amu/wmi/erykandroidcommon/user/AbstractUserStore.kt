package pl.edu.amu.wmi.erykandroidcommon.user

import pl.edu.amu.wmi.erykandroidcommon.service.Token

/**
 * Stworzone przez Eryk Mariankowski dnia 12.01.2018.
 */
abstract class AbstractUserStore<T : Token> {

    var user: T? = null

}