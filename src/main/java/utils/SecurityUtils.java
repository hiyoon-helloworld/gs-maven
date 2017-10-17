package utils;

import exception.ClientException;

public class SecurityUtils {

    public static void checkPath(String path) {
        if (path.contains("../")) {
            throw new ClientException(403, "path access violation...." + path, null);
        }

        if (path.contains(".exe")) {
            throw new ClientException(403, "not allow...." + path, null);
        }
    }
}
