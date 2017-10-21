package pl.edu.amu.wmi.erykandroidcommon.ui.rate

internal class RateResult<T> {

    val item: T

    private val status: String

    val isSuccess: Boolean
        get() = SUCCESS_STATUS == status

    constructor() {
        // retrofit need
    }

    constructor(item: T) {
        this.item = item
        this.status = SUCCESS_STATUS

    }

    companion object {

        private val SUCCESS_STATUS = "updated"
    }

}
