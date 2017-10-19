package pl.edu.amu.wmi.erykandroidcommon.verify;

public class VerificationResult {

    private final String errorMessage;
    private final boolean failure;

    public VerificationResult() {
        this.failure = true;
        this.errorMessage = null;
    }

    public VerificationResult(String errorMessage) {
        this.errorMessage = errorMessage;
        this.failure = true;
    }

    public String getErrorMessage() {

        return errorMessage;
    }

    public boolean isFailure() {
        return failure;
    }

}
