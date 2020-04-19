package com.pppfreak.Hired.serviceimpl;

import com.pppfreak.Hired.customise.Custom;
import com.pppfreak.Hired.Entity.TextileEmployee;
import com.pppfreak.Hired.Entity.Experience;
import com.pppfreak.Hired.repository.EmployeeRepository;
import com.pppfreak.Hired.response.EmployeeResponse;
import com.pppfreak.Hired.form.request.TextileEmployeeRequestForm;
import com.pppfreak.Hired.service.EmployeeService;
import com.pppfreak.Hired.service.ExperienceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.pppfreak.Hired.security.ApplicationUserRole.*;

@Component
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    private ExperienceService experienceService;

    private Custom customise;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository , ExperienceService experienceService ,
                               Custom customise , BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.employeeRepository    = employeeRepository;
        this.experienceService     = experienceService;
        this.customise             = customise;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public TextileEmployee getEmployeeByUserId(String userId) {
        TextileEmployee textileEmployee = employeeRepository.findByUserId(userId);

        if(textileEmployee ==null){
            throw new RuntimeException("Emplopee Not Found "+userId);
        }
        return textileEmployee;

    }

    @Override
    public List<TextileEmployee> getAllEmployee() {
        List<TextileEmployee> textileEmployee = new ArrayList<>();
                employeeRepository
                        .findAll().forEach(textileEmployee::add);
                return textileEmployee;
    }


    @Override
    public EmployeeResponse addEmployee(TextileEmployeeRequestForm employee)  {


        if(employeeRepository.findByEmail(employee.getEmail())!=null){
            throw new RuntimeException("Record already exist ");
        }

        ModelMapper modelMapper = new ModelMapper();
        TextileEmployee theTextileEmployee = modelMapper.map(employee, TextileEmployee.class);
        for (int i = 0; i < theTextileEmployee.getExperienceList().size(); i++) {
            Experience experience = theTextileEmployee
                    .getExperienceList().get(i);
            experienceService.addExperience(experience);
        }

        theTextileEmployee.setUserId(customise.generatedCustomUserId());
        theTextileEmployee.setEncryptedPassword(bCryptPasswordEncoder.encode(employee.getPassword()));
        employeeRepository.save(theTextileEmployee);
        return modelMapper.map(theTextileEmployee , EmployeeResponse.class);

    }

    @Override
    public void updateEmployee(TextileEmployee textileEmployee) {
        employeeRepository.save(textileEmployee);
    }

    @Override
    public void deleteEmployee(Integer id) {
        Optional<TextileEmployee> employee = employeeRepository.findById(id);
        if(!employee.isPresent()){
            throw  new RuntimeException("Employee not found "+id);
        }
        employeeRepository.deleteById(id);
    }

    @Override
    public void deleteEmployeeExperience(Integer employeeId,Integer experienceId) {

        Optional<TextileEmployee> employee = employeeRepository.findById(employeeId);

        Experience experience = experienceService.getExperience(experienceId);

        if(!employee.isPresent()){
            throw  new RuntimeException("Employee not found "+employeeId);
        }
        if(!experience
                .getId()
                .equals(experienceId)){
            throw  new RuntimeException("Experience not present in employee profile "+experienceId);
        }

        experienceService.deleteExperience(experienceId);
    }

    @Override
    public TextileEmployee getEmployee(String email) {
        return  employeeRepository.findByEmail(email);
    }



}
