package utils;

import exception.ClientException;
import http.SimpleServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServletUtils {

    private final static Logger logger = LoggerFactory.getLogger(ServletUtils.class.getCanonicalName());

    public static Class getServletByPackage(String packageName) throws ClientException {
        try {
            Class<SimpleServlet> simpleServletClass = (Class<SimpleServlet>) Class.forName(packageName.replace("/", ""));
            return simpleServletClass;
        } catch (ClassNotFoundException ex) {
            throw new ClientException(404, "packageName not founded: " + packageName, ex);
        }
    }

}
