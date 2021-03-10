package app.web.uploadservice.storage.impl;

import app.web.uploadservice.commons.FileDataResponse;
import app.web.uploadservice.storage.StorageProperties;
import app.web.uploadservice.storage.StorageService;
import app.web.uploadservice.storage.exceptions.FileNotFoundException;
import app.web.uploadservice.storage.exceptions.StorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class FileSystemStorageService implements StorageService {

    private final Path rootLocation;

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    @Override
    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage location", e);
        }
    }

    @Override
    public FileDataResponse store(MultipartFile file, long userId, String city) {

        final String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        final String filePath = userId + "/" + city + "/";

        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new StorageException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, Files.createDirectories(
                        Path.of(this.rootLocation + "/" + filePath)).resolve(filename),
                        StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }

        return new FileDataResponse(filePath + filename);
    }

    @Override
    public Path load(String filePath) {
        return rootLocation.resolve(filePath);
    }

    @Override
    public Resource loadAsResource(String filePath) {
        try {
            Path file = load(filePath);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new FileNotFoundException(
                        "Could not read file in: " + filePath);
            }
        } catch (MalformedURLException e) {
            throw new FileNotFoundException("Could not read file in: " + filePath, e);
        }
    }

    @Override
    public void deleteFile(String filePath) {
        try {
            Path file = load(filePath);
            FileSystemUtils.deleteRecursively(file);
        } catch (IOException e){
            throw new StorageException("Failed to delete file " + filePath, e);
        }

    }
}