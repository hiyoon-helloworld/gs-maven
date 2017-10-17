package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class FileUtils {

    private final static Logger logger = LoggerFactory.getLogger(FileUtils.class.getCanonicalName());

    /**
     * 파일 내용을 반환합니다
     * @param fileName 파일명
     * @return 파일 내용
     * @throws Exception
     */
    public static String getFileContents(String fileName) throws IOException {

        StringBuilder sb = new StringBuilder();
        File file = new File(fileName);
        if (file.isFile()) {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(fileName));
                String line = br.readLine();
                while (line != null) {
                    sb.append(line);
                    line = br.readLine();
                }
            } catch (FileNotFoundException ex) {
                logger.error(ex.getStackTrace().toString());
            } catch (IOException ex) {
                logger.error(ex.getStackTrace().toString());
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (Exception ex) {

                    }
                }
            }
        }

        return sb.toString();
    }

    /**
     * 해당 경로의 파일을 가져옵니다
     * @param object 기준 클래스
     * @param fileName 파일명
     * @return 파일
     */
    public static File setResourcesFile(Object object, String fileName) {
        File result = null;
        try {
            result = new File(object.getClass().getClassLoader().getResource(fileName).getFile());
        } catch (NullPointerException ne) {
        }

        return result;
    }

    /**
     * 문자열을 배열로 변경합니다.
     * @param str 문자
     * @return 배열
     */
    public static String[] splitStr(String str) {
        String[] tokens = str.split("\\s+");
        return tokens;
    }

    /**
     * Socket의 InputStream을 문자열로 반환합니다
     * @param is InputStream
     * @return 문자열
     * @throws IOException Exception
     */
    public static String getRequestToString(InputStream is) throws IOException {
        InputStreamReader isr = null;
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        String content;
        try {
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            while ((content = br.readLine()) != null && content.length() > 0) {
                sb.append(content).append(" ");
                logger.info("#####################" + sb.toString());
            }
        } catch (IOException ioe) {
            logger.error(ioe.getStackTrace().toString());
        } catch (Exception ex) {
            logger.error(ex.getStackTrace().toString());
        }

        return sb.toString();
    }

}
