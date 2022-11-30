package com.pppfreak.Hired.service;

import com.pppfreak.Hired.Entity.JobApplyForm;
import com.pppfreak.Hired.Entity.JobCircular;
import com.pppfreak.Hired.form.request.JobCircularForm;
import com.pppfreak.Hired.response.JobCircularResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface JobCircularService {
    ResponseEntity<JobApplyForm> jobApply(JobApplyForm form, Integer jobId, Integer employeeId);

    ResponseEntity<HttpStatus> createJob(JobCircularForm jobCircularForm, Integer companyId);

    ResponseEntity<JobCircularResponse> disableJobCircular(Integer jobCircularId);

    ResponseEntity<JobCircularResponse> updateJobCircular(JobCircularForm jobCircularForm, Integer id, Integer companyId);

    ResponseEntity<HttpStatus> deleteJobCircular(Integer id);

    JobCircular getJobCircularById(Integer id);

}
