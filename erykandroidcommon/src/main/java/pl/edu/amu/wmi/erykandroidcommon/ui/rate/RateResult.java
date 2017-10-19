package pl.edu.amu.wmi.erykandroidcommon.ui.rate;

public class RateResult<T> {

    private static final String SUCCESS_STATUS = "updated";
    private T item;
    private String status;

    public RateResult() {
        // retrofit need
    }

    public RateResult(T item) {
        this.item = item;
        this.status = SUCCESS_STATUS;

    }

    public T getItem() {
        return item;
    }

    public boolean isSuccess() {
        return SUCCESS_STATUS.equals(status);
    }

}
