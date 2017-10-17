package pojo;

import utils.HttpUtils;

import java.net.Socket;

public class HttpResponse {

    private HttpInfo httpInfo;
    private Socket connection;

    public HttpResponse(Socket connection, HttpInfo httpInfo) {
        this.httpInfo = httpInfo;
        this.connection = connection;
    }

    public void send() {
        HttpUtils.send(httpInfo.getHostInfo().getIndexFile(), httpInfo.getVersion(), httpInfo.getContentType(), this.connection);
    }

    public void send(String title, String content) {
        HttpUtils.send(title, content, httpInfo.getVersion(), httpInfo.getContentType(), this.connection);
    }

    @Override
    public String toString() {
        return "HttpResponse{" +
                "httpInfo=" + httpInfo.toString() +
                '}';
    }
}
