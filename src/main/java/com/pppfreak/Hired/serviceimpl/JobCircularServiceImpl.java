package com.pppfreak.Hired.serviceimpl;

import com.pppfreak.Hired.Entity.*;
import com.pppfreak.Hired.form.request.JobCircularForm;
import com.pppfreak.Hired.repository.*;
import com.pppfreak.Hired.response.JobCircularResponse;
import com.pppfreak.Hired.service.JobCircularService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class JobCircularServiceImpl implements JobCircularService {
    private final CompanyProfileRepository companyProfileRepository;
    private final ModelMapper modelMapper;
    private final JobCircularRepository jobCircularRepository;
    private final JobApplyRepository jobApplyRepository;
    private final EmployeeRepository employeeRepository;
    private final JobCategoryRepository jobCategoryRepository;
    private final CompanyJobTitleRepository companyJobTitleRepository;

    @Autowired
    public JobCircularServiceImpl(CompanyProfileRepository companyProfileRepository , ModelMapper modelMapper ,
                                  JobCircularRepository jobCircularRepository , JobApplyRepository jobApplyRepository ,
                                  EmployeeRepository employeeRepository  ,
                                  JobCategoryRepository jobCategoryRepository ,
                                  CompanyJobTitleRepository companyJobTitleRepository) {
        this.companyProfileRepository  = companyProfileRepository;
        this.modelMapper               = modelMapper;
        this.jobCircularRepository     = jobCircularRepository;
        this.jobApplyRepository        = jobApplyRepository;
        this.employeeRepository        = employeeRepository;
        this.jobCategoryRepository     = jobCategoryRepository;
        this.companyJobTitleRepository = companyJobTitleRepository;
    }

    @Override
    public ResponseEntity<JobApplyForm> jobApply(JobApplyForm form , Integer jobId , Integer employeeId) {

        JobCircular temp = jobCircularRepository
                .findById(jobId)
                .stream()
                .filter(jobCircular -> jobCircular.getId().equals(jobId))
                .filter(JobCircular::isEnable)
                .findFirst()
                .orElseThrow(()->new NullPointerException("Job circular date has been expired"));
        form.setJobCircular(temp);

        Employee employeeById = employeeRepository
                .findById(employeeId)
                .stream()
                .filter(employee -> employee.getId().equals(employeeId))
                .findFirst()
                .orElseThrow(NullPointerException::new);

        List<JobApplyForm> applyForms = new ArrayList<>();
        applyForms.add(form);
        employeeById.setJobApplyForm(applyForms);
        jobApplyRepository.save(form);
        return ResponseEntity.ok(form);
    }

    @Override
    public ResponseEntity<HttpStatus> createJob(JobCircularForm jobCircularForm , Integer companyId) {

        CompanyProfile companyById = companyProfileRepository
                .findById(companyId)
                .stream()
                .filter(companyProfile -> companyProfile
                        .getId()
                        .equals(companyId))
                .findFirst()
                .orElseThrow(() -> new NullPointerException("company not found " + companyId));

        JobCategory jobCategory = jobCategoryRepository.findByCategory(jobCircularForm
                                                                               .getJobCategory()
                                                                               .getCategory());

        CompanyJobTitle companyJobTitle = companyJobTitleRepository.findByJobTitle(jobCircularForm
                                                                                           .getCompanyJobTitle()
                                                                                           .getJobTitle());

        JobCircular job = modelMapper.map(jobCircularForm , JobCircular.class);
        job.setJobCategory(jobCategory);
        job.setCompanyProfile(companyById);
        job.setCompanyJobTitle(companyJobTitle);
        companyById.notifyObserver(companyById);

        jobCircularRepository.save(job);
        return ResponseEntity
                .ok()
                .body(HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<JobCircularResponse> disableJobCircular(Integer jobCircularId) {

        JobCircular job = jobCircularRepository
                .findById(jobCircularId)
                .stream()
                .filter(jobCircular -> jobCircular
                        .getId()
                        .equals(jobCircularId))
                .findFirst()
                .orElseThrow(NullPointerException::new);

        String jobCircularLastDate = job.getApplicationDeadline();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        String currentDateTime = dtf.format(now);

        final int currDay = Integer.parseInt(currentDateTime.substring(8));
        final int jobApplyLastDay = Integer.parseInt(jobCircularLastDate.substring(8));

        if (dtf
                .format(now)
                .equals(jobCircularLastDate) || currDay > jobApplyLastDay) {
            job.setEnable(false);
        }
        jobCircularRepository.save(job);
        return ResponseEntity.ok().body(modelMapper.map(job,JobCircularResponse.class));
    }

    @Override
    public ResponseEntity<JobCircularResponse> updateJobCircular(JobCircularForm jobCircularForm , Integer jobId ,
                                                                 Integer companyId) {

        JobCircular job = jobCircularRepository
                .findById(jobId)
                .stream()
                .filter(jobCircular -> jobCircular.getId().equals(jobId))
                .findFirst()
                .orElseThrow(NullPointerException::new);

        modelMapper.map(jobCircularForm , job);
        CompanyProfile companyById = companyProfileRepository
                .findById(companyId)
                .stream()
                .filter(companyProfile -> companyProfile
                        .getId()
                        .equals(companyId))
                .findFirst()
                .orElseThrow(() -> new NullPointerException("company not found " + companyId));

        JobCategory jobCategory = jobCategoryRepository.findByCategory(jobCircularForm
                                                                               .getJobCategory()
                                                                               .getCategory());

        CompanyJobTitle companyJobTitle = companyJobTitleRepository.findByJobTitle(jobCircularForm
                                                                                           .getCompanyJobTitle()
                                                                                           .getJobTitle());


        job.setJobCategory(jobCategory);
        job.setCompanyProfile(companyById);
        job.setCompanyJobTitle(companyJobTitle);
        companyById.notifyObserver(companyById);

        jobCircularRepository.save(job);
        return ResponseEntity
                .ok()
                .body(modelMapper.map(job , JobCircularResponse.class));
    }

    @Override
    public ResponseEntity<HttpStatus> deleteJobCircular(Integer jobId) {
        JobCircular job = jobCircularRepository
                .findById(jobId)
                .stream()
                .filter(jobCircular -> jobCircular
                        .getId()
                        .equals(jobId))
                .findFirst()
                .orElseThrow(RuntimeException::new);
        return ResponseEntity
                .ok()
                .body(HttpStatus.ACCEPTED);
    }


}
