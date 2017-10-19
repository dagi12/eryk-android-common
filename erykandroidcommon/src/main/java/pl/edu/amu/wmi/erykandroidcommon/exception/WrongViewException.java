package pl.edu.amu.wmi.erykandroidcommon.exception;

public class WrongViewException extends RuntimeException {

    private static final String MESSAGE = "Widok musi być RecyclerView";

    public WrongViewException() {
        super(MESSAGE);
    }

}