package pojo;


import java.util.List;
import java.util.stream.Collectors;

public class ServerInfo {
    private int port;
    private List<HostInfo> hosts;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public List<HostInfo> getHosts() {
        return hosts;
    }

    public void setHosts(List<HostInfo> hosts) {
        this.hosts = hosts;
    }

    @Override
    public String toString() {
        return "ServerInfo{" +
                "port=" + port +
                ", hosts=" + hosts.stream().map(x -> x.toString()).collect(Collectors.joining(",")) +
                '}';
    }
}


