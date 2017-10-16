package pojo;

import java.io.*;
import java.net.Socket;

public class HttpResponse {

    private Writer writer;

    public HttpResponse(Socket connection) throws IOException {
        OutputStream raw = new BufferedOutputStream(connection.getOutputStream());
        this.writer = new OutputStreamWriter(raw);
    }

    public Writer getWriter() {
        return writer;
    }
}
