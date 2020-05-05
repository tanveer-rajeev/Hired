package com.pppfreak.Hired.upload;

import com.pppfreak.Hired.Entity.CseEmployee;
import com.pppfreak.Hired.Entity.Employee;

import com.pppfreak.Hired.customise.EmployeeType;
import com.pppfreak.Hired.repository.CseEmployeeRepository;

import com.pppfreak.Hired.security.LoggedInUserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UploadHelper {

    private final CseEmployeeRepository cseEmployeeRepository;

    public UploadHelper(CseEmployeeRepository cseEmployeeRepository ) {
        this.cseEmployeeRepository = cseEmployeeRepository;
    }


    public void setCseEmployeeResumeLink(String resumeLink,Integer id){
        Optional<CseEmployee> temp = cseEmployeeRepository.findById(id);
        CseEmployee cseEmployee = temp.get();
        cseEmployee.setResumeURL(resumeLink);
        cseEmployeeRepository.save(cseEmployee);
    }



}
