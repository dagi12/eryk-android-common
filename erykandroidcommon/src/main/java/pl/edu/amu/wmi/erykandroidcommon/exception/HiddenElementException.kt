package pl.edu.amu.wmi.erykandroidcommon.exception

internal class HiddenElementException : RuntimeException(MESSAGE) {
    companion object {

        private val MESSAGE = "Przycisk oferujący tą funkcjonalność jest ukryty"
    }
}
