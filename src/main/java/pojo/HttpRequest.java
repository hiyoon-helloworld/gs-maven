package pojo;

public class HttpRequest {
    private HostInfo hostInfo;

    public HttpRequest(HostInfo phostInfo) {
        this.hostInfo = phostInfo;
    }

    public HostInfo getHostInfo() {
        return hostInfo;
    }
}
