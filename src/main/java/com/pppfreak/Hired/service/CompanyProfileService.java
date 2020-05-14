package com.pppfreak.Hired.service;

import com.pppfreak.Hired.Entity.CompanyProfile;
import com.pppfreak.Hired.Entity.JobApplyForm;
import com.pppfreak.Hired.Entity.JobCircular;
import com.pppfreak.Hired.form.request.CompanyProfileModel;

import java.util.Optional;

public interface CompanyProfileService {
    Optional<CompanyProfile> getCompanyProfileById(Integer id);
    void addCompanyProfile(CompanyProfileModel companyProfileModel);
    void jobApply(JobApplyForm form , Integer jobId , Integer employeeId);
    void createJob(JobCircular job,Integer jobCategoryId, Integer companyId);
}
