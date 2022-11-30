package com.pppfreak.Hired.controller;


import com.pppfreak.Hired.upload.StorageService;
import com.pppfreak.Hired.upload.CseEmployeeUploadService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Objects;

@RestController
@RequestMapping("api/resume/upload")
public class FileUploadController {

    private final StorageService storageService;
    private final CseEmployeeUploadService cseEmployeeUploadService;

    @Autowired
    public FileUploadController(StorageService storageService, CseEmployeeUploadService cseEmployeeUploadService) {
        this.storageService = storageService;
        this.cseEmployeeUploadService = cseEmployeeUploadService;
    }


    @PostMapping("cseEmployee/{id}")
    public String uploadFile(@RequestParam("file") MultipartFile file, @PathVariable Integer id) throws IOException {

        storageService.init();
        storageService.storeFile(file);
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String viewResumeURI = ServletUriComponentsBuilder.
                fromCurrentContextPath().path("/upload/").path(fileName).toUriString();

        cseEmployeeUploadService.setResumeLink(viewResumeURI, id);
        return viewResumeURI;
    }

    @GetMapping("/{fileName}")
    public void getResumeUri(@PathVariable String fileName, HttpServletResponse response) throws
            MalformedURLException {

        Resource resource = storageService.loadAsResource(fileName);

        try {

            InputStream inputStream = resource.getInputStream();
            IOUtils.copy(inputStream, response.getOutputStream());
            response.setHeader("Content-Disposition", "inline; filename=Accepted.pdf");

        } catch (IOException ex) {
            throw new RuntimeException("IOError writing file to output stream");
        }
    }


}
