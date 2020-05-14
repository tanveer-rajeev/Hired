package com.pppfreak.Hired.controller;

import com.pppfreak.Hired.Entity.CompanyProfile;
import com.pppfreak.Hired.Entity.JobApplyForm;
import com.pppfreak.Hired.Entity.JobCircular;
import com.pppfreak.Hired.form.request.CompanyProfileModel;
import com.pppfreak.Hired.service.CompanyProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/companies")
public class CompanyProfileController {

    private final CompanyProfileService companyProfileService;

    @Autowired
    public CompanyProfileController(CompanyProfileService companyProfileService) {
        this.companyProfileService = companyProfileService;
    }


    @PostMapping("job/{jobId}/employee/{employeeId}")
    public void jobApply(@RequestBody JobApplyForm form , @PathVariable Integer jobId ,
                         @PathVariable Integer employeeId) {
        companyProfileService.jobApply(form,jobId,employeeId);
    }

    @PostMapping("{companyId}/jobCategory/{jobCategoryId}/job")
    public void createJob(@RequestBody JobCircular job,@PathVariable Integer jobCategoryId,@PathVariable Integer companyId){
       companyProfileService.createJob(job,jobCategoryId,companyId);
    }

    @PostMapping
    public void addCompanyProfile(@RequestBody CompanyProfileModel companyProfileModel){
        companyProfileService.addCompanyProfile(companyProfileModel);
    }

}
