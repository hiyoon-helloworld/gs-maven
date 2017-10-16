package http;

import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;

public interface SimpleServlet {

    void service(HttpRequest httpRequest, HttpResponse httpResponse);
}
