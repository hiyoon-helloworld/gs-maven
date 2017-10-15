package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

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

}
