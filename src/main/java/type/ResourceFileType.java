package type;

public enum ResourceFileType {

    /**
     * 필요한 설정 값의 타입과 위치를 관리합니다.
     */
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
