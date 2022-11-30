package com.pppfreak.Hired.service;

import com.pppfreak.Hired.Entity.CompanyJobTitle;
import com.pppfreak.Hired.Entity.CompanyProfile;
import com.pppfreak.Hired.Entity.JobApplyForm;
import com.pppfreak.Hired.Entity.JobCircular;
import com.pppfreak.Hired.form.request.CompanyProfileModel;
import com.pppfreak.Hired.form.request.JobCircularForm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface CompanyProfileService {
    CompanyProfile getCompanyProfileByName(String name);

    CompanyProfile getCompanyProfileByEmail(String email);

    CompanyJobTitle createJobTitle(CompanyJobTitle companyJobTitle);

    CompanyProfile getCompanyProfileById(Integer id);

    CompanyProfile addCompanyProfile(CompanyProfileModel companyProfileModel);

    ResponseEntity<HttpStatus> subscribeCompany(Integer id, Integer companyId);

    ResponseEntity<HttpStatus> unsubscribeCompany(Integer companyId, Integer employeeId);

    CompanyProfile updateCompanyProfile(CompanyProfileModel companyProfileModel, Integer companyId);

}
