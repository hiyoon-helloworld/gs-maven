package exception;

import java.io.File;

public class ServerException extends Exception {

    private String message;
    private File errorFile;

    public ServerException(final String message, final File errorFile) {
        this.message = message;
        this.errorFile = errorFile;
    }

}
