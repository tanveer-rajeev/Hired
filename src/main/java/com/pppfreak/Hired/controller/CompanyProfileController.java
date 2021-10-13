package com.pppfreak.Hired.controller;

import com.pppfreak.Hired.Entity.*;
import com.pppfreak.Hired.form.request.CompanyProfileModel;
import com.pppfreak.Hired.service.CompanyProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/companies")
public class CompanyProfileController {

    private final CompanyProfileService companyProfileService;

    @Autowired
    public CompanyProfileController(CompanyProfileService companyProfileService) {
        this.companyProfileService = companyProfileService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<CompanyProfile> getCompanyProfileById(@PathVariable Integer id){

        return ResponseEntity.ok().body(companyProfileService.getCompanyProfileById(id));
    }

    @GetMapping("name/{name}")
    public ResponseEntity<CompanyProfile> getCompanyProfileByName(@PathVariable String name){

        return ResponseEntity.ok().body(companyProfileService.getCompanyProfileByName(name));
    }
    @GetMapping("email/{email}")
    public ResponseEntity<CompanyProfile> getCompanyProfileByEmail(@PathVariable String email){

        return ResponseEntity.ok().body(companyProfileService.getCompanyProfileByEmail(email));
    }


    @PostMapping
    public ResponseEntity<CompanyProfile> addCompanyProfile(@RequestBody CompanyProfileModel companyProfileModel){
        return ResponseEntity.ok()
                .body(companyProfileService
                        .addCompanyProfile(companyProfileModel));
    }

    @PostMapping("/jobTitle")
    public ResponseEntity<CompanyJobTitle> addJobTitle(@RequestBody  CompanyJobTitle companyJobTitle){
        return ResponseEntity.ok()
                .body(companyProfileService
                        .createJobTitle(companyJobTitle));
    }

    @PostMapping("subscribe/{companyId}/employee/{employeeId}")
    public ResponseEntity<HttpStatus> subscribeCompany(@PathVariable Integer companyId,@PathVariable Integer employeeId){
        companyProfileService.subscribeCompany(companyId,employeeId);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }


    @PostMapping("/unsubscribe/{companyId}/{employeeId}")
    public ResponseEntity<HttpStatus> unsubscribe(@PathVariable Integer companyId,@PathVariable Integer employeeId){
        companyProfileService.unsubscribeCompany(companyId,employeeId);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyProfile> updateCompanyProfile(@RequestBody CompanyProfileModel companyProfileModel,
                                                               @PathVariable Integer id){
        return ResponseEntity.ok().body(companyProfileService.updateCompanyProfile(companyProfileModel,id));
    }
}
