package exception;

public class ClientException extends RuntimeException {

    private int code;
    private String message;
    private String stackTrace;

    public ClientException(final int pcode, final String pmessage, final Exception ex) {
        super(ex);
        this.code = pcode;
        this.message = pmessage;
        this.stackTrace = ex.getStackTrace().toString();
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
