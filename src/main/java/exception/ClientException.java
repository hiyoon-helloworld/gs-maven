package exception;

import java.io.File;

public class ClientException extends Exception {
    private String message;
    private File errorFile;

    public ClientException(final String message, final File errorFile) {
        this.message = message;
        this.errorFile = errorFile;
    }
}
