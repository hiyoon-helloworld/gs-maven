package service;

import http.SimpleServlet;
import org.joda.time.LocalTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.HttpRequest;
import pojo.HttpResponse;

public class DateNow implements SimpleServlet {

    private final static Logger logger = LoggerFactory.getLogger(Hello.class.getCanonicalName());

    @Override
    public void service(HttpRequest httpRequest, HttpResponse httpResponse) {
        logger.info("Called service.Http Servlet");
        LocalTime currentTime = new LocalTime();
        httpResponse.send("Current Time", "Current Time: " + currentTime.toString());
    }
}
