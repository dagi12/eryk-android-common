package pl.edu.amu.wmi.erykandroidcommon.exception;

public class HiddenElementException extends RuntimeException {

    private static final String MESSAGE = "Przycisk oferujący tą funkcjonalność jest ukryty";

    public HiddenElementException() {
        super(MESSAGE);
    }
}
