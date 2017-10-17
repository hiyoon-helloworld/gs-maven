package http;

import exception.ClientException;
import exception.ServerException;
import pojo.*;
import utils.FileUtils;
import utils.HttpUtils;
import utils.SecurityUtils;
import utils.ServletUtils;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RequestProcessor implements Runnable {

    private final static Logger logger = Logger.getLogger(RequestProcessor.class.getCanonicalName());
    private Socket connection;
    private ServerInfo serverInfo;

    public RequestProcessor(ServerInfo serverInfo, Socket connection) {
        this.serverInfo = serverInfo;
        this.connection = connection;
    }

    @Override
    public void run() {
        HttpInfo httpInfo = null;
        try {
            // request 정보를 설정합니다
            String[] tokens = FileUtils.splitStr(FileUtils.getRequestToString(connection.getInputStream()));
            httpInfo = new HttpInfo(tokens, this.serverInfo);

            // security check
            SecurityUtils.checkPath(tokens[1]);

            // 해당 servlet을 찾아 요청을 보냅니다
            HttpRequest request = new HttpRequest(httpInfo);
            HttpResponse response = new HttpResponse(connection, httpInfo);
            SimpleServlet simpleServlet = (SimpleServlet)ServletUtils.getServletByPackage(httpInfo.getMapping()).newInstance();
            simpleServlet.service(request, response);

        } catch (ClientException ex) { // Client Exception
            logger.log(Level.WARNING, "Error talking to " + connection.getRemoteSocketAddress(), ex);
            HttpUtils.send((ex.getCode() == 403 ? httpInfo.getHostInfo().getError403File() : httpInfo.getHostInfo().getError404File()),
                    httpInfo.getVersion(),
                    httpInfo.getContentType(),
                    connection);
        } catch (ServerException ex) { // Server Exception
            logger.log(Level.WARNING, "Error talking to " + connection.getRemoteSocketAddress(), ex);
            HttpUtils.send(httpInfo.getHostInfo().getError500File(),
                    httpInfo.getVersion(),
                    httpInfo.getContentType(),
                    connection);
        } catch (Exception ex) { // Server Exception
            logger.log(Level.WARNING, "Error talking to " + connection.getRemoteSocketAddress(), ex);
            HttpUtils.send(httpInfo.getHostInfo().getError500File(),
                    httpInfo.getVersion(),
                    httpInfo.getContentType(),
                    connection);
        } finally {
            try {
                connection.close();
            } catch (IOException ex) {
            }
        }
    }
}