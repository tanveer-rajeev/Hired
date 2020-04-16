package com.pppfreak.Hired.ServiceImpl;

import com.pppfreak.Hired.Customise.Custom;
import com.pppfreak.Hired.Entity.Employee;
import com.pppfreak.Hired.Entity.Experience;
import com.pppfreak.Hired.DAO.EmployeeRepository;
import com.pppfreak.Hired.Entity.Employee_Response;
import com.pppfreak.Hired.request_response_Model.EmployeeRequestModel;
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
    public Employee getEmployeeByUserId(String userId) {
        Employee employee= employeeRepository.findByUserId(userId);

        if(employee==null){
            throw new RuntimeException("Emplopee Not Found "+userId);
        }
        return employee;

    }

    @Override
    public List<Employee> getAllEmployee() {
        List<Employee> employee = new ArrayList<>();
                employeeRepository
                        .findAll().forEach(employee::add);
                return employee;
    }


    @Override
    public Employee_Response addEmployee(EmployeeRequestModel employee)  {


        if(employeeRepository.findByEmail(employee.getEmail())!=null){
            throw new RuntimeException("Record already exist ");
        }

        ModelMapper modelMapper = new ModelMapper();
        Employee theEmployee = modelMapper.map(employee,Employee.class);
        for (int i = 0; i < theEmployee.getExperienceList().size(); i++) {
            Experience experience = theEmployee.getExperienceList().get(i);
            experienceService.addExperience(experience);
        }

        theEmployee.setUserId(customise.generatedCustomUserId());
        theEmployee.setEncryptedPassword(bCryptPasswordEncoder.encode(employee.getPassword()));
        employeeRepository.save(theEmployee);
        return modelMapper.map(theEmployee, Employee_Response.class);

    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Integer id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if(!employee.isPresent()){
            throw  new RuntimeException("Employee not found "+id);
        }
        employeeRepository.deleteById(id);
    }

    @Override
    public void deleteEmployeeExperience(Integer employeeId,Integer experienceId) {

        Optional<Employee> employee = employeeRepository.findById(employeeId);

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
    public Employee getEmployee(String email) {
        return  employeeRepository.findByEmail(email);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByEmail(email);
        if(employee==null){
            throw new UsernameNotFoundException("User not available  "+email);
        }
        return new User(employee.getEmail(),employee.getEncryptedPassword(), EMPLOYEE.getGrantedAuthorities());
    }
}
