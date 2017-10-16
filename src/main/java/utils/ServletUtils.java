package utils;

import http.SimpleServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServletUtils {

    Logger logger = LoggerFactory.getLogger(ServletUtils.class);

    public Class getServletByPackage(String packageName) throws ClassNotFoundException {
        try {
            Class<SimpleServlet> simpleServletClass = (Class<SimpleServlet>) Class.forName("service.Hello");
            return simpleServletClass;
        } catch (ClassNotFoundException e) {
            logger.error("Class not founed. packageName: {}, error: {}", packageName, e);
            throw e;
        }
    }

}
