package pl.edu.amu.wmi.erykandroidcommon.verify

class VerificationResult {

    val errorMessage: String?

    val isFailure: Boolean

    constructor() {
        this.isFailure = true
        this.errorMessage = null
    }

    constructor(errorMessage: String) {
        this.errorMessage = errorMessage
        this.isFailure = true
    }
}
