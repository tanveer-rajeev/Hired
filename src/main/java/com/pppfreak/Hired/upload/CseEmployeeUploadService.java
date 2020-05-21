package com.pppfreak.Hired.upload;

import com.pppfreak.Hired.Entity.CseEmployee;

import com.pppfreak.Hired.repository.CseEmployeeRepository;

import com.pppfreak.Hired.service.UploadService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CseEmployeeUploadService implements UploadService {

    private final CseEmployeeRepository cseEmployeeRepository;

    public CseEmployeeUploadService(CseEmployeeRepository cseEmployeeRepository) {
        this.cseEmployeeRepository = cseEmployeeRepository;
    }

    @Override
    public void setResumeLink(String resumeLink , Integer id) {
        CseEmployee cseEmployee = cseEmployeeRepository.findById(id).stream().filter(cseEmployee1 -> cseEmployee1.getId().equals(id))
                                            .findFirst().orElseThrow(NullPointerException::new);
        cseEmployee.setResumeURL(resumeLink);
        cseEmployeeRepository.save(cseEmployee);
    }
}
