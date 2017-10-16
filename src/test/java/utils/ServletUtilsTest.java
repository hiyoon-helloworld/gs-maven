package utils;

import http.SimpleServlet;
import org.junit.Test;

public class ServletUtilsTest {

    @Test
    public void getServletByPackageTest() {
        try {
            ServletUtils servletUtils = new ServletUtils();
            Class cls = servletUtils.getServletByPackage("");
            SimpleServlet simpleServlet = (SimpleServlet) cls.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
