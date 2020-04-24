package com.pppfreak.Hired.controller;

import com.pppfreak.Hired.Entity.Employee;
import com.pppfreak.Hired.security.LoggedInUserDetails;
import com.pppfreak.Hired.upload.StorageService;
import com.pppfreak.Hired.upload.UploadHelper;
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
public class FileUploadController {

    private final StorageService storageService;
    private final UploadHelper uploadHelper;

    @Autowired
    public FileUploadController(StorageService storageService , UploadHelper uploadHelper) {
        this.storageService = storageService;
        this.uploadHelper   = uploadHelper;
    }


    @PostMapping("/upload/{id}")
    public String uploadFile(@RequestParam("file") MultipartFile file , @PathVariable Integer id) throws IOException {
        storageService.deleteAll();
        storageService.init();
        storageService.storeFile(file);
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String viewResumeURI = ServletUriComponentsBuilder.
                fromCurrentContextPath().path("/getFile/").path(fileName).toUriString();
        Employee employee = LoggedInUserDetails.getUserEntity();

       // uploadHelper.saveEmployeeWithResumeURL(employee.getDepartment() , viewResumeURI , id);
        return viewResumeURI;
    }

    @GetMapping("/getFile/{fileName}")
    public void getResumeUri(@PathVariable String fileName , HttpServletResponse response) throws
            MalformedURLException {

        Resource resource = storageService.loadAsResource(fileName);

        try {

            InputStream inputStream = resource.getInputStream();
            IOUtils.copy(inputStream,response.getOutputStream());
            response.setHeader("Content-Disposition", "inline; filename=Accepted.pdf");

        } catch (IOException ex) {
            throw new RuntimeException("IOError writing file to output stream");
        }
    }


}
