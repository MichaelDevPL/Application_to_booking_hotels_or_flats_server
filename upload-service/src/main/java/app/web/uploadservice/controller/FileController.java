package app.web.uploadservice.controller;

import app.web.uploadservice.commons.FileDataResponse;
import app.web.uploadservice.storage.StorageService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FileController {

    private final StorageService storageService;

    public FileController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/upload-multiple-files/{userId}/{city}")
    @ResponseBody
    public List<FileDataResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files,
                                                      @PathVariable long userId,
                                                      @PathVariable String city) {

        return Arrays.stream(files)
                .map(file -> storageService.store(file, userId, city))
                .collect(Collectors.toList());
    }

    @GetMapping("/download")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(@RequestParam String filePath) {

        Resource resource = storageService.loadAsResource(filePath);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @DeleteMapping("/delete-image/{userId}/{city}/{image}")
    public void deleteImageFile(@PathVariable String userId,
                                @PathVariable String city,
                                @PathVariable String image){
        storageService.deleteFile(userId + "/" + city + "/" + image);
    }


}
