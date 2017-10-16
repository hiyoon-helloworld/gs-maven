package http;

import pojo.HttpRequest;
import pojo.HttpResponse;

public interface SimpleServlet {

    void service(HttpRequest httpRequest, HttpResponse httpResponse);
}
