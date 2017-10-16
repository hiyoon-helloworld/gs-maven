package exception;

public class ServerException extends RuntimeException {

    private String message;

    public ServerException(final String message) {
        this.message = message;
    }

}
