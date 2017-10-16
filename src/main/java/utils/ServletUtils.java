package utils;

import exception.ClientException;
import http.SimpleServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServletUtils {

    private static Logger logger = LoggerFactory.getLogger(ServletUtils.class);

    public static Class getServletByPackage(String packageName) throws ClientException {
        try {
            Class<SimpleServlet> simpleServletClass = (Class<SimpleServlet>) Class.forName("service.Hello");
            return simpleServletClass;
        } catch (ClassNotFoundException e) {
            logger.error("Class not founed. packageName: {}, error: {}", packageName, e);
            throw new ClientException(404, e.getStackTrace().toString());
        }
    }

}
