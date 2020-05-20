package com.pppfreak.Hired.serviceimpl;

import com.pppfreak.Hired.Entity.*;
import com.pppfreak.Hired.customise.MassageConstant;
import com.pppfreak.Hired.form.request.CseEmployeeRequestForm;

import com.pppfreak.Hired.repository.*;
import com.pppfreak.Hired.response.CseEmployeeResponse;
import com.pppfreak.Hired.security.LoggedInUserDetails;
import com.pppfreak.Hired.service.CseEmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CseEmployeeServiceImpl implements CseEmployeeService {

    private final ModelMapper modelMapper;
    private final CseEmployeeRepository cseEmployeeRepository;
    private final JobFieldRepository jobFieldRepository;
    private final ExpertSkillRepository expertSkillRepository;
    private final SecondarySkillRepository secondarySkillRepository;
    private final UniversityBscRepository universityBscRepository;
    @Autowired
    public CseEmployeeServiceImpl(ModelMapper modelMapper , CseEmployeeRepository cseEmployeeRepository ,
                                  JobFieldRepository jobFieldRepository , ExpertSkillRepository expertSkillRepository ,
                                  SecondarySkillRepository secondarySkillRepository ,
                                  UniversityBscRepository universityBscRepository) {
        this.modelMapper           = modelMapper;
        this.cseEmployeeRepository = cseEmployeeRepository;
        this.jobFieldRepository    = jobFieldRepository;
        this.expertSkillRepository = expertSkillRepository;

        this.secondarySkillRepository = secondarySkillRepository;
        this.universityBscRepository  = universityBscRepository;
    }

    public Employee loggedInEmployee(){
        return LoggedInUserDetails.getUserEntity();
    }

    @Override
    public List<CseEmployee> getAllCseEmployee(){
        return (List<CseEmployee>) cseEmployeeRepository.findAll();
    }

    @Override
    public CseEmployeeResponse addCseEmployee(CseEmployeeRequestForm requestForm) {

        CseEmployee cseEmployee = modelMapper.map(requestForm,CseEmployee.class);
        Employee employee = loggedInEmployee();
        cseEmployee.setEmployee(employee);

        cseEmployee.setAvailableForJob(requestForm
                                               .getAvailableForJob()
                                               .equals("yes"));

        UniversityBsc universityBsc = universityBscRepository.findByUniversityName(
                                       requestForm.getUniversityBsc().getUniversityName()
        );
        cseEmployee.setUniversityBsc(universityBsc);

        JobField jobRole = jobFieldRepository.findByField(requestForm.getJobField().getField());
        cseEmployee.setJobField(jobRole);

        Set<ExpertSkill> expertSkills = requestForm.getExpertSkills();
        Set<ExpertSkill> temp = new HashSet<>();

        for (ExpertSkill p : expertSkills) {
            p = expertSkillRepository.findBySkill(p.getSkill());
            temp.add(new ExpertSkill(p.getId() , ""));

        }
        Set<SecondarySkill> secondarySkills = requestForm.getSecondarySkills();
        Set<SecondarySkill> secondSkill = new HashSet<>();

        for (SecondarySkill s : secondarySkills) {
            s = secondarySkillRepository.findBySkill(s.getSkill());
            secondSkill.add(new SecondarySkill(s.getId() , ""));
        }
        cseEmployee.setSecondarySkills(secondSkill);
        cseEmployee.setExpertSkills(temp);

        cseEmployeeRepository.save(cseEmployee);
        return modelMapper.map(cseEmployee,CseEmployeeResponse.class);
    }

    @Override
    public CseEmployeeResponse updateCseEmployee(CseEmployeeRequestForm requestForm,Integer id) {

        CseEmployee cseEmployee =cseEmployeeRepository.findById(id).stream()
                                  .filter(cseEmployee1 -> cseEmployee1.getId().equals(id))
                                  .findFirst()
                                  .orElseThrow(NullPointerException::new);
        modelMapper.map(requestForm,cseEmployee);

        UniversityBsc universityBsc = universityBscRepository.findByUniversityName(
                requestForm.getUniversityBsc().getUniversityName()
        );
        cseEmployee.setUniversityBsc(universityBsc);

        JobField jobRole = jobFieldRepository.findByField(requestForm.getJobField().getField());
        cseEmployee.setJobField(jobRole);

        Set<ExpertSkill> expertSkills = requestForm.getExpertSkills();
        Set<ExpertSkill> temp = new HashSet<>();

        for (ExpertSkill p : expertSkills) {
            p = expertSkillRepository.findBySkill(p.getSkill());
            temp.add(p);

        }
        Set<SecondarySkill> secondarySkills = requestForm.getSecondarySkills();
        Set<SecondarySkill> secondSkill = new HashSet<>();

        for (SecondarySkill s : secondarySkills) {
            s = secondarySkillRepository.findBySkill(s.getSkill());
            secondSkill.add(s);
        }
        cseEmployee.setSecondarySkills(secondSkill);
        cseEmployee.setExpertSkills(temp);

        cseEmployeeRepository.save(cseEmployee);
        return modelMapper.map(cseEmployee,CseEmployeeResponse.class);

    }

    @Override
    public String deleteCseEmployee(Integer id) {
        cseEmployeeRepository.deleteById(id);
        return MassageConstant.SUCCESS.name();
    }


    @Override
    public Optional<CseEmployee> getCseEmployeeById(Integer id){
        Optional<CseEmployee> employeeRepositoryById = cseEmployeeRepository.findById(id);
         Optional<CseEmployee> cseEmployee = Optional.empty();
        if(employeeRepositoryById.isPresent()){
            cseEmployee= employeeRepositoryById;
        }
        return cseEmployee;
    }

}
