package type;

import java.io.File;

public enum ResourceFileType {

    SERVER(0);

    private int value;
    private static String[] arrFile = {"server-resource.json"};

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
            return this.getClass().getClassLoader().getResource(fileName).getFile();
        }
    }

}
