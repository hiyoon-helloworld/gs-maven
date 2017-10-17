package service;

import http.SimpleServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.HttpRequest;
import pojo.HttpResponse;

public class Hello implements SimpleServlet {

    private final static Logger logger = LoggerFactory.getLogger(Hello.class.getCanonicalName());

    /**
     * 해당 html를 반환합니다.
     * @param httpRequest HttpRequest
     * @param httpResponse HttpResponse
     */
    @Override
    public void service(HttpRequest httpRequest, HttpResponse httpResponse) {
        logger.info("Called service.Http Servlet");
        httpResponse.send();
    }
}
