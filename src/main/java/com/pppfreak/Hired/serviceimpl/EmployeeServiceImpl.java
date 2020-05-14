package com.pppfreak.Hired.serviceimpl;

import com.pppfreak.Hired.Entity.*;
import com.pppfreak.Hired.customise.MassageConstant;
import com.pppfreak.Hired.customise.Utils;
import com.pppfreak.Hired.form.request.EmployeeRequestForm;
import com.pppfreak.Hired.repository.CompanyProfileRepository;
import com.pppfreak.Hired.repository.EmployeeRepository;
import com.pppfreak.Hired.repository.JobCategoryRepository;
import com.pppfreak.Hired.response.EmployeeResponse;
import com.pppfreak.Hired.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final JobCategoryRepository jobCategoryRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final Utils utils;
    private final ModelMapper modelMapper;
    private final CompanyProfileRepository companyProfileRepository;
    @Autowired
    public EmployeeServiceImpl( EmployeeRepository employeeRepository ,
                               JobCategoryRepository jobCategoryRepository ,
                               BCryptPasswordEncoder bCryptPasswordEncoder , Utils utils , ModelMapper modelMapper ,
                               CompanyProfileRepository companyProfileRepository) {
        this.employeeRepository    = employeeRepository;
        this.jobCategoryRepository = jobCategoryRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.utils                 = utils;
        this.modelMapper           = modelMapper;
        this.companyProfileRepository = companyProfileRepository;
    }

    @Override
    public Employee getEmployeeById(Integer id) {

      Optional<Employee> employee= employeeRepository.findById(id);
      return  employee.get();

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
    public EmployeeResponse getUserByUserId(String userId) {

        Optional<Employee> employee = Optional.ofNullable(employeeRepository.findByUserId(userId));

        Employee theEmployee = null;
        if (employee.isPresent()) {
            theEmployee = employee.get();
        } else {
            throw new UsernameNotFoundException("User not found  " + userId);
        }

        return modelMapper.map(theEmployee , EmployeeResponse.class);
    }

    @Override
    public EmployeeResponse signUp(EmployeeRequestForm employeeRequestForm) {

        if (employeeRepository.findByEmail(employeeRequestForm.getEmail()) != null) {
            throw new RuntimeException("Record already exist ");
        }
        EmployeeResponse employeeResponse = modelMapper.map(employeeRequestForm , EmployeeResponse.class);
        Employee employee = modelMapper.map(employeeRequestForm , Employee.class);

        JobCategory jobCategory = jobCategoryRepository.findByCategory(employee.getJobCategory().getCategory());
        employee.setJobCategory(new JobCategory(jobCategory.getId(),""));

        employee.setEncryptedPassword(bCryptPasswordEncoder.encode(employeeRequestForm.getPassword()));
        employee.setUserId(utils.generatedCustomUserId());
        
        employee.setEmployeeType(EmployeeType.EMPLOYEE);
        employeeRepository.save(employee);
        return employeeResponse;

    }

    @Override
    public List<Employee> getALlEmployee() {
        return (List<Employee>) employeeRepository.findAll();
    }

    @Override
    public EmployeeResponse updateEmployee(EmployeeRequestForm employeeRequestForm , String id) {
        Optional<Employee> checkDuplicateEmail =
                Optional.ofNullable(employeeRepository.findByEmail(employeeRequestForm.getEmail()));
        if (checkDuplicateEmail.isPresent()) {
            throw new RuntimeException("Record already exist " + employeeRequestForm.getEmail());
        }
        Employee employee = employeeRepository.findByUserId(id);
        employee.setEmail(employeeRequestForm.getEmail());
        employee.setEncryptedPassword(bCryptPasswordEncoder.encode(employeeRequestForm.getPassword()));
        employeeRepository.save(employee);

        return modelMapper.map(employee , EmployeeResponse.class);

    }

    @Override
    public String deleteEmployee(String userId) {
        Employee employee = employeeRepository.findByUserId(userId);
        employeeRepository.delete(employee);
        return MassageConstant.SUCCESS.toString();
    }

    @Override
    public String deleteEmployeeById(Integer id) {
        employeeRepository.deleteById(id);
        return MassageConstant.SUCCESS.toString();
    }

    @Override
    public List<CseEmployee> getCseEmployeeByEmployeeUserId(String userId) {

        return employeeRepository.findByUserId(userId).getCseEmployeeList();
    }

    @Override
    public void subscribeCompany( Integer companyId,  Integer employeeId){
        Optional<Employee> emp = employeeRepository.findById(employeeId);
        Employee employee = emp.get();

        Optional<CompanyProfile> tempCompany = companyProfileRepository.findById(companyId);
        CompanyProfile company =  tempCompany.get();
        company.registerObserver(employee);    // company get employee subscriber

        // employee get company subscription
        List<CompanyProfile> companies = new ArrayList<>();
        companies.add(company);
        employee.setSubscribedCompanies(companies);
        employeeRepository.save(employee);
    }

}
