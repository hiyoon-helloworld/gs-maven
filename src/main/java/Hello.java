import http.SimpleServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.HttpRequest;
import pojo.HttpResponse;

public class Hello implements SimpleServlet {

    private final static Logger logger = LoggerFactory.getLogger(Hello.class.getCanonicalName());

    @Override
    public void service(HttpRequest httpRequest, HttpResponse httpResponse) {
        logger.info("Called Http Servlet");
        httpResponse.send();
    }
}
