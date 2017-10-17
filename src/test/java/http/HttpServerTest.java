package http;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;

public class HttpServerTest {

    private final static Logger logger = LoggerFactory.getLogger(HttpServerTest.class.getCanonicalName());
    private final static String SERVER_URL = "127.0.0.1";
    private final static int PORT = 9090;

    @Before
    public void init() throws Exception {
        HttpServer.main(null);
    }

    /**
     * HOST Header 해석1
      */
    @Test
    public void testHeader1() {
        try {
            // 소켓을 생성하여 연결을 요청한다.
            Socket socket = new Socket(SERVER_URL, PORT);

            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            writer.print("GET /Greeting HTTP/1.1 Host: www.naver.com\r\n");
            writer.print("\r\n");
            writer.flush();

            // 소켓의 입력스트림을 얻는다.
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String content = null;
            while ((content = in.readLine()) != null) {
                sb.append(content);
            }

            Assert.assertTrue(sb.toString().contains("NAVER"));

            // 스트림과 소켓을 닫는다.
            socket.close();
        } catch (ConnectException ce) {
            logger.error(ce.getMessage() + ce.getStackTrace().toString());
        } catch (IOException ie) {
            logger.error(ie.getMessage() + ie.getStackTrace().toString());
        } catch (Exception e) {
            logger.error(e.getMessage() + e.getStackTrace().toString());
        }
    }

    /**
     * HOST Header 해석2
     */
    @Test
    public void testHeader2() {
        try {
            // 소켓을 생성하여 연결을 요청한다.
            Socket socket = new Socket(SERVER_URL, PORT);

            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            writer.print("GET /Greeting HTTP/1.1 Host: www.nhn.com\r\n");
            writer.print("\r\n");
            writer.flush();

            // 소켓의 입력스트림을 얻는다.
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String content = null;
            while ((content = in.readLine()) != null) {
                sb.append(content);
            }

            Assert.assertTrue(sb.toString().contains("NHN"));

            // 스트림과 소켓을 닫는다.
            socket.close();
        } catch (ConnectException ce) {
            logger.error(ce.getMessage() + ce.getStackTrace().toString());
        } catch (IOException ie) {
            logger.error(ie.getMessage() + ie.getStackTrace().toString());
        } catch (Exception e) {
            logger.error(e.getMessage() + e.getStackTrace().toString());
        }
    }

    /**
     * ERROR Test - 403
     */
    @Test
    public void testError1() {
        try {
            // 소켓을 생성하여 연결을 요청한다.
            Socket socket = new Socket(SERVER_URL, PORT);

            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            writer.print("GET /date HTTP/1.1 Host: www.nhn.com\r\n");
            writer.print("\r\n");
            writer.flush();

            // 소켓의 입력스트림을 얻는다.
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String content = null;
            while ((content = in.readLine()) != null) {
                sb.append(content);
            }

            Assert.assertTrue(sb.toString().contains("403"));

            // 스트림과 소켓을 닫는다.
            socket.close();
        } catch (ConnectException ce) {
            logger.error(ce.getMessage() + ce.getStackTrace().toString());
        } catch (IOException ie) {
            logger.error(ie.getMessage() + ie.getStackTrace().toString());
        } catch (Exception e) {
            logger.error(e.getMessage() + e.getStackTrace().toString());
        }
    }

    /**
     * ERROR Test - .exe
     */
    @Test
    public void testError2() {
        try {
            // 소켓을 생성하여 연결을 요청한다.
            Socket socket = new Socket(SERVER_URL, PORT);

            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            writer.print("GET /date.exe HTTP/1.1 Host: www.nhn.com\r\n");
            writer.print("\r\n");
            writer.flush();

            // 소켓의 입력스트림을 얻는다.
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String content = null;
            while ((content = in.readLine()) != null) {
                sb.append(content);
            }

            Assert.assertTrue(sb.toString().contains("403"));

            // 스트림과 소켓을 닫는다.
            socket.close();
        } catch (ConnectException ce) {
            logger.error(ce.getMessage() + ce.getStackTrace().toString());
        } catch (IOException ie) {
            logger.error(ie.getMessage() + ie.getStackTrace().toString());
        } catch (Exception e) {
            logger.error(e.getMessage() + e.getStackTrace().toString());
        }
    }

    /**
     * Get DateTime
     */
    @Test
    public void testDateTimeServlet() {
        try {
            // 소켓을 생성하여 연결을 요청한다.
            Socket socket = new Socket(SERVER_URL, PORT);

            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            writer.print("GET /server.Now HTTP/1.1 Host: www.nhn.com\r\n");
            writer.print("\r\n");
            writer.flush();

            // 소켓의 입력스트림을 얻는다.
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String content = null;
            while ((content = in.readLine()) != null) {
                sb.append(content);
            }

            Assert.assertTrue(sb.toString().contains("Current Time"));

            // 스트림과 소켓을 닫는다.
            socket.close();
        } catch (ConnectException ce) {
            logger.error(ce.getMessage() + ce.getStackTrace().toString());
        } catch (IOException ie) {
            logger.error(ie.getMessage() + ie.getStackTrace().toString());
        } catch (Exception e) {
            logger.error(e.getMessage() + e.getStackTrace().toString());
        }
    }
}
