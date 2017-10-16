package pojo;

import java.net.URLConnection;
import java.util.List;

public class HttpInfo {

    private String method;
    private String mapping;
    private String version;
    private String host;
    private String contentType;
    private HostInfo hostInfo;

    public HttpInfo(String[] tokens, List<HostInfo> phostInfos) {
        this.method = tokens.length > 0 ? tokens[0] : "";
        this.mapping = tokens.length > 1 ? tokens[1] : "";
        this.version = tokens.length > 2 ? tokens[2] : "";
        for (int i = 0, cnt = tokens.length; i < cnt; i++) {
            String token = tokens[i].toLowerCase();
            if (token.contains("host") && i + 1 < cnt) {
                host = tokens[i + 1];
            }
            else if (token.contains("content-type") && i + 1 < cnt) {
                contentType = tokens[i + 1];
            }
        }

        if (this.host == null || this.host.length() < 1) {
            this.hostInfo = phostInfos.get(0);
        }
        if (this.contentType == null || this.contentType.length() < 1) {
            this.contentType = URLConnection.getFileNameMap().getContentTypeFor(this.contentType);
        }

        this.hostInfo = phostInfos
                .stream()
                .filter(x -> x.getHostName().toLowerCase().contains(this.host))
                .findFirst()
                .orElse(phostInfos.get(0));
    }

    public String getMethod() {
        return method;
    }

    public String getMapping() {
        return mapping;
    }

    public String getVersion() {
        return version;
    }

    public String getHost() {
        return host;
    }

    public String getContentType() {
        return contentType;
    }

    public HostInfo getHostInfo() {
        return hostInfo;
    }
}
