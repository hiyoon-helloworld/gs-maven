package pojo;

public class HttpRequest {
    private HttpInfo httpInfo;

    public HttpRequest(HttpInfo httpInfo) {
        this.httpInfo = httpInfo;
    }

    public HttpInfo getHttpInfo() {
        return httpInfo;
    }

    @Override
    public String toString() {
        return "HttpRequest{" +
                "httpInfo=" + httpInfo.toString() +
                '}';
    }
}
