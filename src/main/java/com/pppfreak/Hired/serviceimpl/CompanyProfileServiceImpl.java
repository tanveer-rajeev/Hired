package com.pppfreak.Hired.serviceimpl;

import com.pppfreak.Hired.Entity.*;
import com.pppfreak.Hired.form.request.CompanyProfileModel;
import com.pppfreak.Hired.repository.*;
import com.pppfreak.Hired.service.CompanyProfileService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class CompanyProfileServiceImpl implements CompanyProfileService {

    private final CompanyProfileRepository companyProfileRepository;
    private final ModelMapper modelMapper;
    private final JobCircularRepository jobCircularRepository;
    private final EmployeeJobApplyRepository employeeJobApplyRepository;
    private final EmployeeRepository employeeRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JobCategoryRepository jobCategoryRepository;
    @Autowired
    public CompanyProfileServiceImpl(CompanyProfileRepository companyProfileRepository , ModelMapper modelMapper ,
                                     JobCircularRepository jobCircularRepository , EmployeeJobApplyRepository employeeJobApplyRepository ,
                                     EmployeeRepository employeeRepository ,
                                     BCryptPasswordEncoder bCryptPasswordEncoder ,
                                     JobCategoryRepository jobCategoryRepository) {
        this.companyProfileRepository = companyProfileRepository;
        this.modelMapper              = modelMapper;
        this.jobCircularRepository      = jobCircularRepository;
        this.employeeJobApplyRepository = employeeJobApplyRepository;
        this.employeeRepository         = employeeRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jobCategoryRepository = jobCategoryRepository;
    }


    @Override
    public Optional<CompanyProfile> getCompanyProfileById(Integer id) {
        return companyProfileRepository.findById(id).stream().findFirst();
    }

    @Override
    public void addCompanyProfile(CompanyProfileModel companyProfileModel) {
       CompanyProfile companyProfile = modelMapper.map(companyProfileModel , CompanyProfile.class);
       companyProfile.setEncryptedPassword(bCryptPasswordEncoder.encode(companyProfileModel.getPassword()));
       companyProfileRepository.save(companyProfile);

    }

    @Override
    public void jobApply(JobApplyForm form , Integer jobId , Integer employeeId) {
        Optional<JobCircular> job = jobCircularRepository.findById(jobId);
        JobCircular temp = job.get();
        form.setJobCircular(temp);

        Optional<Employee> emp = employeeRepository.findById(employeeId);
        Employee employee = emp.get();
        List<JobApplyForm> applyForms = new ArrayList<>();
        applyForms.add(form);
        employee.setJobApplyForm(applyForms);
        employeeJobApplyRepository.save(form);
    }

    @Override
    public void createJob(JobCircular job ,Integer jobCategoryId, Integer companyId) {
        Optional<CompanyProfile> companyById = companyProfileRepository.findById(companyId);
        Optional<JobCategory> jobCategoryById = jobCategoryRepository.findById(jobCategoryId);

        JobCategory jobCategory = jobCategoryById.get();
        CompanyProfile companyProfile = companyById.get();
        job.setJobCategory(jobCategory);
        job.setCompanyProfile(companyProfile);

        companyProfile.notifyObserver(companyProfile);

        jobCircularRepository.save(job);
    }
}
