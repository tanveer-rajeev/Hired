package com.pppfreak.Hired.upload;

import com.pppfreak.Hired.Entity.CseEmployee;
import com.pppfreak.Hired.repository.CseEmployeeRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UploadHelper {

    private final CseEmployeeRepository cseEmployeeRepository;

    public UploadHelper(CseEmployeeRepository cseEmployeeRepository) {
        this.cseEmployeeRepository = cseEmployeeRepository;
    }

    public void saveEmployeeWithResumeURL(String resumeUrl , Integer id) {

        Optional<CseEmployee> temp = cseEmployeeRepository.findById(id);
        CseEmployee cseEmployee = temp.get();
        cseEmployee.setResumeURL(resumeUrl);
        cseEmployeeRepository.save(cseEmployee);
        //return MassageConstant.FAILED_TO_UPLOAD.toString();
    }

}
