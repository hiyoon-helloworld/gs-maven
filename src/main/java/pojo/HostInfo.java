package pojo;

import utils.FileUtils;

import java.io.IOException;

public class HostInfo {
    private String hostName;
    private String rootDir;
    private String index;
    private String error403;
    private String error404;
    private String error500;
    //    private File indexFile;
//    private File error403File;
//    private File error404File;
//    private File error500File;
    private String indexFile;
    private String error403File;
    private String error404File;
    private String error500File;

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getRootDir() {
        return rootDir;
    }

    public void setRootDir(String rootDir) {
        this.rootDir = rootDir;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) throws IOException {
        this.index = index;
        this.indexFile = FileUtils.getFileContentAsStream(this.rootDir + this.index);
    }

    public String getError403() {
        return error403;
    }

    public void setError403(String error403) throws IOException {
        this.error403 = error403;
        this.error403File = FileUtils.getFileContentAsStream(this.rootDir + this.error403);
    }

    public String getError404() {
        return error404;
    }

    public void setError404(String error404) throws IOException {
        this.error404 = error404;
        this.error404File = FileUtils.getFileContentAsStream(this.rootDir + this.error404);
    }

    public String getError500() {
        return error500;
    }

    public void setError500(String error500) throws IOException {
        this.error500 = error500;
        this.error500File = FileUtils.getFileContentAsStream(this.rootDir + this.error500);
    }

    public String getIndexFile() {
        return indexFile;
    }

    public String getError403File() {
        return error403File;
    }

    public String getError404File() {
        return error404File;
    }

    public String getError500File() {
        return error500File;
    }

    @Override
    public String toString() {
        return "HostInfo{" +
                "hostName='" + hostName + '\'' +
                ", rootDir='" + rootDir + '\'' +
                ", index='" + index + '\'' +
                ", error403='" + error403 + '\'' +
                ", error404='" + error404 + '\'' +
                ", error500='" + error500 + '\'' +
                ", indexFile=" + indexFile +
                ", error403File=" + (error403File != null ? error403File : "null") +
                ", error404File=" + (error404File != null ? error404File : "null") +
                ", error500File=" + (error500File != null ? error500File : "null") +
                '}';
    }
}
