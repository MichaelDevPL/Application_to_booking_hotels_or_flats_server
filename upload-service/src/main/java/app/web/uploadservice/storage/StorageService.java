package app.web.uploadservice.storage;

import app.web.uploadservice.commons.FileDataResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface StorageService {

    void init();

    FileDataResponse store(MultipartFile file, long userId, String city);

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteFile(String filePath);
}
