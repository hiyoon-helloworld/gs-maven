package utils;

import org.junit.Assert;
import org.junit.Test;

public class ServletUtilsTest {

    @Test
    public void getServletByPackageTest() {
        ServletUtils servletUtils = new ServletUtils();
        Class cls = servletUtils.getServletByPackage("service.Hello");
        Assert.assertNotNull(cls);
    }
}
