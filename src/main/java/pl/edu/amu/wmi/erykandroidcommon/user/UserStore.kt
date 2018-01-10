package pl.edu.amu.wmi.erykandroidcommon.user

import pl.edu.amu.wmi.erykandroidcommon.di.CommonApplication

/**
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@247.codes> on 19.11.17.
 */
const val IS_SIGNED = "IS_SIGNED"

class UserStore {

    private var _isSigned = false

    var isSigned: Boolean
        get() = _isSigned
        set(value) {
            this._isSigned
            CommonApplication.preferences.getBoolean(IS_SIGNED).set(value)
        }

    init {
        _isSigned = CommonApplication.preferences.getBoolean(IS_SIGNED).get()
    }


}
