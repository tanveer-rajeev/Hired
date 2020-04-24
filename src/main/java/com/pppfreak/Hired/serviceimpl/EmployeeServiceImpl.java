package com.pppfreak.Hired.serviceimpl;

import com.pppfreak.Hired.Entity.Employee;
import com.pppfreak.Hired.customise.MassageConstant;
import com.pppfreak.Hired.customise.Utils;
import com.pppfreak.Hired.form.request.EmployeeRequestForm;
import com.pppfreak.Hired.repository.EmployeeRepository;
import com.pppfreak.Hired.response.EmployeeResponse;
import com.pppfreak.Hired.security.UserEntity;
import com.pppfreak.Hired.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.pppfreak.Hired.security.ApplicationUserRole.EMPLOYEE;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final Utils utils;
    private final ModelMapper modelMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository ,
                               BCryptPasswordEncoder bCryptPasswordEncoder , Utils utils ,
                               ModelMapper modelMapper) {
        this.employeeRepository    = employeeRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.utils                 = utils;
        this.modelMapper           = modelMapper;
    }

    @Override
    public Employee getUserByEmail(String email) {
        Employee employee = employeeRepository.findByEmail(email);
        if (employee == null) {
            throw new UsernameNotFoundException("User not found  " + email);
        }
        return employee;
    }

    @Override
    public Employee getUserByUserId(String userId) {

//        if (employee == null) {
//            throw new UsernameNotFoundException("User not found  " + userId);
//        }
        return  employeeRepository.findByUserId(userId);
    }

    @Override
    public EmployeeResponse signUp(EmployeeRequestForm employeeRequestForm) {

        if (employeeRepository.findByEmail(employeeRequestForm.getEmail()) != null) {
            throw new RuntimeException("Record already exist ");
        }
        EmployeeResponse employeeResponse = modelMapper.map(employeeRequestForm,EmployeeResponse.class);
        Employee employee = modelMapper.map(employeeRequestForm , Employee.class);
        employee.setEncryptedPassword(bCryptPasswordEncoder.encode(employeeRequestForm.getPassword()));
        employee.setUserId(utils.generatedCustomUserId());
        employeeRepository.save(employee);
        return employeeResponse;

    }

    @Override
    public List<Employee> getALlEmployee() {
        return (List<Employee>) employeeRepository.findAll();
    }

    @Override
    public EmployeeResponse updateEmployee(EmployeeRequestForm employeeRequestForm,String id) {
        Optional<Employee> checkDuplicateEmail =
                Optional.ofNullable(employeeRepository.findByEmail(employeeRequestForm.getEmail()));
        if(checkDuplicateEmail.isPresent()){
            throw new RuntimeException("Record already exist "+employeeRequestForm.getEmail());
        }
        Employee employee = getUserByUserId(id);
        employee.setEmail(employeeRequestForm.getEmail());
        employee.setEncryptedPassword(bCryptPasswordEncoder.encode(employeeRequestForm.getPassword()));
        employeeRepository.save(employee);

        return modelMapper.map(employee,EmployeeResponse.class);

    }

    @Override
    public String deleteEmployee(String userId) {
        Employee employee = getUserByUserId(userId);
        employeeRepository.delete(employee);
        return MassageConstant.SUCCESS.toString();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByEmail(email);
        if (employee == null) {
            throw new UsernameNotFoundException("User not available  " + email);
        }
        return new UserEntity(employee.getEmail() , employee.getEncryptedPassword() ,
                EMPLOYEE.getGrantedAuthorities() , true , true , true , true);
    }

}
