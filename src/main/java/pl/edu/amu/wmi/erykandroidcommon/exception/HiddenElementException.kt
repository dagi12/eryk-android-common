package pl.edu.amu.wmi.erykandroidcommon.exception

const val MESSAGE = "Przycisk oferujący tą funkcjonalność jest ukryty"

class HiddenElementException : RuntimeException(MESSAGE)