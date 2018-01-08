package pl.edu.amu.wmi.erykandroidcommon.exception

class WrongViewException : RuntimeException(MESSAGE) {
    companion object {

        private val MESSAGE = "Widok musi być RecyclerView"
    }
}