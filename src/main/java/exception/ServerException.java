package exception;

public class ServerException extends RuntimeException {

    private String message;
    private String stackTrace;

    public ServerException(final String message, final Exception ex) {
        super(ex);
        this.message = message;
        this.stackTrace = ex.getStackTrace().toString();
    }

}
