package exception;

public class ClientException extends RuntimeException {
    private int code;
    private String message;

    public ClientException(final int pcode, final String pmessage) {
        this.code = pcode;
        this.message = pmessage;
    }
}
