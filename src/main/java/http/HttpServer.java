package http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.ServerInfo;
import type.ResourceFileType;
import utils.JsonUtils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {

    private static final Logger logger = LoggerFactory.getLogger(HttpServer.class);
    private static final int NUM_THREADS = 50;
    private static ServerInfo serverInfo;

    public static void main(String[] args) {
        logger.info("The Http Server Init...");
        try {
            // 서버 정보를 설정합니다
            serverInfo = JsonUtils.getJsonToMap(ResourceFileType.SERVER);
            logger.info(serverInfo.toString());

            // 서버를 시작합니다
            HttpServer server = new HttpServer();
            server.start();
        } catch (Exception ex) {
            logger.error("The Http Server Cannnot init.", ex);
        }
    }

    public HttpServer() throws IOException {
    }

    public void start() throws IOException {
        ExecutorService pool = Executors.newFixedThreadPool(NUM_THREADS);
        try (ServerSocket server = new ServerSocket(serverInfo.getPort())) {
            logger.info("Accepting connections on port " + server.getLocalPort());
            while (true) {
                try {
                    Socket request = server.accept();
                    Runnable r = new RequestProcessor(serverInfo, request);
                    pool.submit(r);
                } catch (IOException ex) {
                    logger.error("Error accepting connection", ex);
                }
            }
        }
    }
}
