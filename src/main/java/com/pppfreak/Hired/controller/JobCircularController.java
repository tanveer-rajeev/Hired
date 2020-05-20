package com.pppfreak.Hired.controller;

import com.pppfreak.Hired.Entity.JobApplyForm;
import com.pppfreak.Hired.form.request.JobCircularForm;
import com.pppfreak.Hired.response.JobCircularResponse;
import com.pppfreak.Hired.service.JobCircularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("jobCircular")
public class JobCircularController {

    private final JobCircularService jobCircularService;

    @Autowired
    public JobCircularController(JobCircularService jobCircularService) {
        this.jobCircularService = jobCircularService;
    }
    @PostMapping("job/{jobId}/employee/{employeeId}")
    public ResponseEntity<JobApplyForm> jobApply(@RequestBody JobApplyForm form , @PathVariable Integer jobId ,
                                   @PathVariable Integer employeeId) {
        return jobCircularService.jobApply(form,jobId,employeeId);

    }

    @PostMapping("/{companyId}/createJob")
    public ResponseEntity<HttpStatus> createJob(@RequestBody JobCircularForm job,
                                                @PathVariable Integer companyId){
        return  jobCircularService.createJob(job, companyId);

    }

    @PutMapping("/company/{companyId}/job/{jobId}")
    public ResponseEntity<JobCircularResponse> updateJob(@RequestBody JobCircularForm jobCircularForm, @PathVariable Integer companyId
                                               , @PathVariable Integer jobId){
        return jobCircularService.updateJobCircular(jobCircularForm,jobId,companyId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteJobCircular(@PathVariable Integer id){
         return jobCircularService.deleteJobCircular(id);
    }

    @PostMapping("disable/{jobId}")
    public ResponseEntity<JobCircularResponse> disableJobCircular(@PathVariable Integer jobId){
        return jobCircularService.disableJobCircular(jobId);
    }
}
