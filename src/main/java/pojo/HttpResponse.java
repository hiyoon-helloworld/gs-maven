package pojo;

import exception.ServerException;
import utils.HttpUtils;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;

public class HttpResponse {

    private Writer writer;
    private HttpInfo httpInfo;
    private Socket connection;

    public HttpResponse(Socket connection, HttpInfo httpInfo) {
        try {
            OutputStream raw = new BufferedOutputStream(connection.getOutputStream());
            this.writer = new OutputStreamWriter(raw);
            this.httpInfo = httpInfo;
            this.connection = connection;
        } catch (IOException ex) {
            throw new ServerException(ex.getStackTrace().toString());
        }
    }

    public Writer getWriter() {
        return writer;
    }

    public void send() {
        try {
            if (httpInfo.getHostInfo().getIndexFile().canRead()) {
                OutputStream raw = new BufferedOutputStream(connection.getOutputStream());
                byte[] theData = Files.readAllBytes(httpInfo.getHostInfo().getIndexFile().toPath());
                if (httpInfo.getVersion().startsWith("HTTP/")) { // send a MIME header
                    HttpUtils.sendHeader(writer, "HTTP/1.0 200 OK", httpInfo.getContentType(), theData.length);
                }
                // send the file; it may be an image or other binary data
                // so use the underlying output stream
                // instead of the writer
                raw.write(theData);
                raw.flush();
            }
        } catch (IOException ex) {
            throw new ServerException(ex.getStackTrace().toString());
        }
    }
}
