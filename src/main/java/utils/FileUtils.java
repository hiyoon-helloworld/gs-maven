package utils;

import java.io.*;

public class FileUtils {

    public static String getFileContents(String fileName) throws Exception {

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
            } catch (Exception ex) {
                throw ex;
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (Exception ex) {

                    }
                }
            }

        }
        else {
            throw new IllegalArgumentException("not exist file. fileName: " + fileName);
        }

        return sb.toString();
    }

    public static File setResourcesFile(Object object, String fileName) {
        File result = null;
        try {
            result = new File(object.getClass().getClassLoader().getResource(fileName).getFile());
        } catch (NullPointerException ne) {
            // Ignore...
            // todo default file??
        }

        return result;
    }

    public static String[] splitStr(String str) {
        String[] tokens = str.split("\\s+");
        return tokens;
    }

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
            }
        } catch (IOException ioe) {
            System.out.println("IO Exception occurred");
            ioe.printStackTrace();
        } finally {
            isr.close();
            br.close();
        }

        return sb.toString();
    }

}
