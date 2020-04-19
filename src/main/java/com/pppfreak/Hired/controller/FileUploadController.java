package com.pppfreak.Hired.controller;

import com.pppfreak.Hired.repository.EmployeeRepository;
import com.pppfreak.Hired.Entity.TextileEmployee;
import com.pppfreak.Hired.upload.StorageService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@RestController
public class FileUploadController {

    private final StorageService storageService;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public FileUploadController(StorageService storageService , EmployeeRepository employeeRepository) {
        this.storageService = storageService;
        this.employeeRepository = employeeRepository;
    }

    @PostMapping("/upload/{id}")
    public String uploadFile(@RequestParam("file") MultipartFile file,@PathVariable String id) {
        //storageService.deleteAll();
        storageService.init();

        try {
            storageService.storeFile(file);
            String fileName= StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            String viewResumeURI= ServletUriComponentsBuilder.
                                  fromCurrentContextPath()
                                  .path("/getFile/")
                                  .path(fileName)
                                  .toUriString();

            TextileEmployee textileEmployee = employeeRepository.findByUserId(id);

            if (textileEmployee ==null){
                throw new RuntimeException("Employee not found "+id);
            }
            textileEmployee.setResumeURL(viewResumeURI);
            employeeRepository.save(textileEmployee);
            return viewResumeURI;

        } catch (IOException e) {
            throw new RuntimeException("File not found "+file.getOriginalFilename());
        }
    }

    @GetMapping("/getFile/{fileName}")
    @PreAuthorize("hasAuthority('employee:read')")
    public void viewFile(@PathVariable String fileName, HttpServletResponse response){
        Resource resource = storageService.loadAsResource(fileName);

        try {
            InputStream inputStream = resource.getInputStream();
            IOUtils.copy(inputStream,response.getOutputStream());
            response.setHeader("Content-Disposition","inline; filename=Accepted.pdf");


        } catch (IOException e) {
            throw new RuntimeException("File not found "+fileName);
        }
    }


}
