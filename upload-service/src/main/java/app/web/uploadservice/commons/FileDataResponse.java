package app.web.uploadservice.commons;

public class FileDataResponse {
    private String path;

    public FileDataResponse() {
    }

    public FileDataResponse(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
