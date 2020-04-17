package com.pppfreak.Hired.fileUpload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class StorageServiceImpl implements StorageService {

    private final Path rootLocation;

    @Autowired
    public StorageServiceImpl(StorageProperties storageProperties) {
        this.rootLocation = Paths.get(storageProperties.getLocation());
    }

    @Override
    public void init() {

        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("File not found from root location");
        }
    }

    @Override
    public void storeFile(MultipartFile file) {

        String fileName = file.getOriginalFilename();
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("File not found exception");
            }
            if (fileName.contains("..")) {
                throw new NullPointerException("File should have an employee name");
            }
            InputStream inputStream = file.getInputStream();
            Files.copy(inputStream , this.rootLocation.resolve(fileName) , StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException ex){
            throw new RuntimeException("File not found exception");
        }

    }

    @Override
    public Path load(String fileName) {
        return rootLocation.resolve(fileName);
    }

    @Override
    public Resource loadAsResource(String fileName) {

        Path path = load(fileName);
        try {
            Resource resource = new UrlResource(path.toUri());
            if(resource.isReadable()||resource.exists()){
                return resource;
            }else {
                throw  new RuntimeException("File not readable "+fileName);
            }
        } catch (MalformedURLException e) {
            throw  new RuntimeException("Could not read file "+fileName);
        }

    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }
}
