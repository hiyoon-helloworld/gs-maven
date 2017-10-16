package http;

import exception.ClientException;
import exception.ServerException;
import pojo.*;
import utils.FileUtils;
import utils.ServletUtils;

import java.io.*;
import java.net.Socket;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RequestProcessor implements Runnable {
    private final static Logger logger = Logger.getLogger(RequestProcessor.class.getCanonicalName());
//    private File rootDirectory;
//    private String indexFileName = "index.html";
    private Socket connection;
    private ServerInfo serverInfo;

    public RequestProcessor(ServerInfo serverInfo, Socket connection) {
//        if (rootDirectory.isFile()) {
//            throw new IllegalArgumentException(
//                    "rootDirectory must be a directory, not a file");
//        }

//        String rootPath = this.getClass().getClassLoader().getResource("html/nhn").getPath();
//        this.rootDirectory = new File(rootPath);

//        try {
////            rootDirectory = rootDirectory.getCanonicalFile();
//           String rootPath = this.getClass().getClassLoader().getResource("/html/nhn").getPath();
//            rootDirectory = new File(rootPath);
//        } catch (IOException ex) {
//        }
//        this.rootDirectory = rootDirectory;
//        if (indexFileName != null)
//            this.indexFileName = indexFileName;

        this.serverInfo = serverInfo;
        this.connection = connection;
    }

    @Override
    public void run() {
        HostInfo hostInfo = null;
        try {
            String[] tokens = FileUtils.splitStr(FileUtils.getRequestToString(connection.getInputStream()));
            HttpInfo httpInfo = new HttpInfo(tokens, this.serverInfo.getHosts());
            HttpRequest request = new HttpRequest(httpInfo);
            HttpResponse response = new HttpResponse(this.connection, httpInfo);
            hostInfo = httpInfo.getHostInfo();
            SimpleServlet simpleServlet = (SimpleServlet)ServletUtils.getServletByPackage(httpInfo.getMapping()).newInstance();
            simpleServlet.service(request, response);
        } catch (IllegalAccessException ex) {
            logger.log(Level.WARNING, "Error talking to " + connection.getRemoteSocketAddress(), ex);
        } catch (InstantiationException ex) {
            logger.log(Level.WARNING, "Error talking to " + connection.getRemoteSocketAddress(), ex);
        } catch (IOException ex) {
            logger.log(Level.WARNING, "Error talking to " + connection.getRemoteSocketAddress(), ex);
        } catch (ServerException ex) {
            logger.log(Level.WARNING, "Error talking to " + connection.getRemoteSocketAddress(), ex);
        } catch (ClientException ex) {
            logger.log(Level.WARNING, "Error talking to " + connection.getRemoteSocketAddress(), ex);
        } finally {
            try {
                connection.close();
            } catch (IOException ex) {
            }
        }
    }

    /*
    @Override
    public void run() {
        // for security checks
//        String root = rootDirectory.getPath();
        String root = "";
        String indexFileName = "index.html";
        String rootDirectory = "/";

        try {
            OutputStream raw = new BufferedOutputStream(connection.getOutputStream());
            Writer out = new OutputStreamWriter(raw);
            Reader in = new InputStreamReader(new BufferedInputStream(connection.getInputStream()), "UTF-8");
            String get = FileUtils.getRequestToString(connection.getInputStream());
            System.out.println(get);


//            int cnt = 0;
//            while (true) {
//                int c = in.read();
//                if (c == '\r' || c == '\n') {
//                    cnt++;
//                }
//                else if (c < -1) {
//                    in.close();
//                    break;
//                }
//
//                requestLine.append((char) c);
//                System.out.println(requestLine);
//            }


//            String get = requestLine.toString();
            logger.info(connection.getRemoteSocketAddress() + " " + get);
            String[] tokens = get.split("\\s+");
            String method = tokens[0];
            String version = "";
            if (method.equals("GET")) {
                String fileName = tokens[1];
                if (fileName.endsWith("/")) fileName += indexFileName;
                String contentType =
                        URLConnection.getFileNameMap().getContentTypeFor(fileName);
                if (tokens.length > 2) {
                    version = tokens[2];
                }
                File theFile = new File(rootDirectory, fileName.substring(1, fileName.length()));
                if (theFile.canRead()
                        && theFile.getCanonicalPath().startsWith(root)) { // Don't let clients outside the document root
                    byte[] theData = Files.readAllBytes(theFile.toPath());
                    if (version.startsWith("HTTP/")) { // send a MIME header
                        sendHeader(out, "HTTP/1.0 200 OK", contentType, theData.length);
                    }
                    // send the file; it may be an image or other binary data
                    // so use the underlying output stream
                    // instead of the writer
                    raw.write(theData);
                    raw.flush();
                } else {
                    // can't find the file
                    String body = new StringBuilder("<HTML>\r\n")
                            .append("<HEAD><TITLE>File Not Found</TITLE>\r\n")
                            .append("</HEAD>\r\n")
                            .append("<BODY>")
                            .append("<H1>HTTP Error 404: File Not Found</H1>\r\n")
                            .append("</BODY></HTML>\r\n")
                            .toString();
                    if (version.startsWith("HTTP/")) { // send a MIME header
                        sendHeader(out, "HTTP/1.0 404 File Not Found", "text/html; charset=utf-8", body.length());
                    }
                    out.write(body);
                    out.flush();
                }
            } else {
                // method does not equal "GET"
                String body = new StringBuilder("<HTML>\r\n").append("<HEAD><TITLE>Not Implemented</TITLE>\r\n").append("</HEAD>\r\n")
                        .append("<BODY>")
                        .append("<H1>HTTP Error 501: Not Implemented</H1>\r\n")
                        .append("</BODY></HTML>\r\n").toString();
                if (version.startsWith("HTTP/")) { // send a MIME header
                    sendHeader(out, "HTTP/1.0 501 Not Implemented",
                            "text/html; charset=utf-8", body.length());
                }
                out.write(body);
                out.flush();
            }
        } catch (IOException ex) {
            logger.log(Level.WARNING, "Error talking to " + connection.getRemoteSocketAddress(), ex);
        } finally {
            try {
                connection.close();
            } catch (IOException ex) {
            }
        }
    }
    */

//    private void sendHeader(Writer out, String responseCode, String contentType, int length)
//            throws IOException {
//        out.write(responseCode + "\r\n");
//        Date now = new Date();
//        out.write("Date: " + now + "\r\n");
//        out.write("Server: JHTTP 2.0\r\n");
//        out.write("Content-length: " + length + "\r\n");
//        out.write("Content-type: " + contentType + "\r\n\r\n");
//        out.flush();
//    }
}