package com.pppfreak.Hired.upload;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

public interface StorageService {

    void init();
    void storeFile(MultipartFile file);
    Path load(String fileName);
    Resource loadAsResource(String fileName);
    void deleteAll();

}
