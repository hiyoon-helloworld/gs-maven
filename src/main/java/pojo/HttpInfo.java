package pojo;

import exception.ClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.stream.Collectors;

public class HttpInfo {

    private final static Logger logger = LoggerFactory.getLogger(HttpInfo.class.getCanonicalName());

    private String method;
    private String mapping;
    private String version;
    private String host;
    private String contentType = "text/html";
    private HostInfo hostInfo;

    public HttpInfo(String[] tokens, ServerInfo serverInfo) {
        try {
            // method
            this.method = tokens.length > 0 ? tokens[0] : "";

            // version
            this.version = tokens.length > 2 ? tokens[2] : "";
            for (int i = 0, cnt = tokens.length; i < cnt; i++) {
                String token = tokens[i].toLowerCase();
                if (token.contains("Host:") && i + 1 < cnt) {
                    host = tokens[i + 1];
                } else if (token.contains("content-type") && i + 1 < cnt) {
                    contentType = tokens[i + 1];
                }
            }

            // host
            if (this.host == null || this.host.length() < 1) {
                this.hostInfo = serverInfo.getHosts().get(0);
            }
            else {
                this.hostInfo = serverInfo.getHosts()
                        .stream()
                        .filter(x -> x.getHostName().toLowerCase().contains(this.host))
                        .findFirst()
                        .orElse(serverInfo.getHosts().get(0));
            }

            // mapping
            this.mapping = serverInfo.getServlets()
                    .stream()
                    .filter(x -> x.containsKey(tokens[1]))
                    .map(x -> x.get(tokens[1]))
                    .findFirst()
                    .orElse("");

        } catch (Exception ex) {
            throw new ClientException(404, "tokens: " + Arrays.stream(tokens).collect(Collectors.joining(", ")), ex);
        }
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

    @Override
    public String toString() {
        return "HttpInfo{" +
                "method='" + method + '\'' +
                ", mapping='" + mapping + '\'' +
                ", version='" + version + '\'' +
                ", host='" + host + '\'' +
                ", contentType='" + contentType + '\'' +
                ", hostInfo=" + hostInfo.toString() +
                '}';
    }
}
