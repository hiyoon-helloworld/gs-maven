package utils;

import exception.ServerException;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.util.Date;

public class HttpUtils {

    /**
     * Heder를 설정합니다.
     * @param out Writer
     * @param responseCode Response Code
     * @param contentType Content-Type
     * @param length Length
     * @throws IOException IOException
     */
    public static void sendHeader(Writer out, String responseCode, String contentType, int length)
            throws IOException {
        out.write(responseCode + "\r\n");
        Date now = new Date();
        out.write("Date: " + now + "\r\n");
        out.write("Server: JHTTP 2.0\r\n");
        out.write("Content-length: " + length + "\r\n");
        out.write("Content-type: " + contentType + "\r\n\r\n");
        out.flush();
    }

    /**
     * 파일내용을 읽어 반환합니다.
     * @param file File
     * @param version Version
     * @param contentType Content-Type
     * @param connection Connection
     */
    public static void send(File file, String version, String contentType, Socket connection) {
        try {
            if (file.canRead()) {
                OutputStream raw = new BufferedOutputStream(connection.getOutputStream());
                Writer writer = new OutputStreamWriter(raw);
                byte[] theData = Files.readAllBytes(file.toPath());
                if (version.startsWith("HTTP/")) { // send a MIME header
                    sendHeader(writer, "HTTP/1.0 200 OK", contentType, theData.length);
                }

                raw.write(theData);
                raw.flush();
            }
        } catch (IOException ex) {
            throw new ServerException("Response send error.", ex);
        }
    }

    /**
     * 해당 Title, Content를 Html형식으로 만들어 반환합니다.
     * @param title title
     * @param content content
     * @param version Version
     * @param contentType Content-Type
     * @param connection Connection
     */
    public static void send(String title, String content, String version, String contentType, Socket connection) {

        try {
            OutputStream raw = new BufferedOutputStream(connection.getOutputStream());
            Writer out = new OutputStreamWriter(raw);
            // can't find the file
            String body = new StringBuilder("<HTML>\r\n")
                    .append("<HEAD><TITLE>")
                    .append(title)
                    .append("</TITLE>\r\n")
                    .append("</HEAD>\r\n")
                    .append("<BODY>")
                    .append("<H1>")
                    .append(content)
                    .append("</H1>\r\n")
                    .append("</BODY></HTML>\r\n")
                    .toString();
            if (version.startsWith("HTTP/")) { // send a MIME header
                sendHeader(out, "HTTP/1.0 404 File Not Found", "text/html; charset=utf-8", body.length());
            }
            out.write(body);
            out.flush();
        } catch (IOException ex) {
            throw new ServerException("Response send error.", ex);
        }
    }
}
