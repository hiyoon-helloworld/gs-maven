package type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public enum ResourceFileType {

    /**
     * 필요한 설정 값의 타입과 위치를 관리합니다.
     */
    SERVER(0);

    private int value;
    private static String[] arrFile = {"server-resource.json"};
    private final static Logger logger = LoggerFactory.getLogger(ResourceFileType.class.getCanonicalName());
    ResourceFileType(int pvalue) {
        this.value = pvalue;
    }

    public String getValue() {
        String fileName = null;
        switch (this.value) {
            case 0:
                fileName = arrFile[this.value];
        }

        if (fileName == null || fileName.length() < 1) {
            throw new IllegalArgumentException("Not Found Resource File Type");
        }
        else {
            return fileName;
        }
    }

}
