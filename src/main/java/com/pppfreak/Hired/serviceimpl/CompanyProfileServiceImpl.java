package com.pppfreak.Hired.serviceimpl;

import com.pppfreak.Hired.Entity.*;
import com.pppfreak.Hired.form.request.CompanyProfileModel;
import com.pppfreak.Hired.repository.*;
import com.pppfreak.Hired.service.CompanyProfileService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class CompanyProfileServiceImpl implements CompanyProfileService {

    private final CompanyProfileRepository companyProfileRepository;
    private final ModelMapper modelMapper;
    private final EmployeeRepository employeeRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CompanyJobTitleRepository companyJobTitleRepository;

    @Autowired
    public CompanyProfileServiceImpl(CompanyProfileRepository companyProfileRepository , ModelMapper modelMapper ,
                                     EmployeeRepository employeeRepository ,
                                     BCryptPasswordEncoder bCryptPasswordEncoder ,

                                     CompanyJobTitleRepository companyJobTitleRepository) {
        this.companyProfileRepository = companyProfileRepository;
        this.modelMapper              = modelMapper;
        this.employeeRepository    = employeeRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.companyJobTitleRepository = companyJobTitleRepository;
    }


    @Override
    public CompanyProfile getCompanyProfileByName(String name) {
        return companyProfileRepository.findByCompanyName(name);
    }

    @Override
    public CompanyProfile getCompanyProfileByEmail(String email) {


        return companyProfileRepository.findByEmail(email);
    }

    @Override
    public CompanyJobTitle createJobTitle(CompanyJobTitle companyJobTitle) {
        return companyJobTitleRepository.save(companyJobTitle);
    }

    @Override
    public CompanyProfile getCompanyProfileById(Integer id) {
        return companyProfileRepository.findById(id).stream()
                .filter(companyProfile -> companyProfile.getId().equals(id))
                .findFirst()
                .orElseThrow(NullPointerException::new);
    }

    @Override
    public CompanyProfile addCompanyProfile(CompanyProfileModel companyProfileModel) {
        if(companyProfileRepository.findByEmail(companyProfileModel.getEmail())!=null){
            throw new RuntimeException("company already exist ");
        }
       CompanyProfile companyProfile = modelMapper.map(companyProfileModel , CompanyProfile.class);
       companyProfile.setEncryptedPassword(bCryptPasswordEncoder.encode(companyProfileModel.getPassword()));
       companyProfileRepository.save(companyProfile);
       return companyProfile;
    }

    @Override
    public ResponseEntity<HttpStatus> subscribeCompany( Integer companyId,  Integer employeeId){

        Employee employee = employeeRepository.findById(employeeId).stream()
                .filter(employee1 -> employee1.getId().equals(employeeId))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("employee not found "+employeeId));

        CompanyProfile company = companyProfileRepository.findById(companyId).stream()
                .filter(companyProfile -> companyProfile.getId().equals(companyId))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("company not found "+companyId));

        company.registerObserver(employee);    // company get employee subscriber

        List<CompanyProfile> companies = new ArrayList<>();
        companies.add(company);
        employee.setSubscribedCompanies(companies);
        employeeRepository.save(employee);
        return ResponseEntity.ok().body(HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<HttpStatus> unsubscribeCompany(Integer companyId , Integer employeeId) {
        Employee employee = employeeRepository.findById(employeeId).stream()
                .filter(employee1 -> employee1.getId().equals(employeeId))
                .findFirst()
                .orElseThrow(NullPointerException::new);

        CompanyProfile company = companyProfileRepository.findById(companyId).stream()
                .filter(companyProfile -> companyProfile.getId().equals(companyId))
                .findFirst()
                .orElseThrow(NullPointerException::new);

        List<Employee> employees = company.getUnsubscribeList();
        employees.add(employee);
        company.setUnsubscribeList(employees);
         companyProfileRepository.save(company);
        return ResponseEntity.ok().body(HttpStatus.ACCEPTED);
    }

    @Override
    public CompanyProfile updateCompanyProfile(CompanyProfileModel companyProfileModel ,
                                                               Integer companyId) {
        CompanyProfile company = companyProfileRepository.findById(companyId).stream()
                .filter(companyProfile -> companyProfile.getId().equals(companyId))
                .findFirst()
                .orElseThrow(NullPointerException::new);
        modelMapper.map(companyProfileModel,company);
        companyProfileRepository.save(company);
        return company;
    }
}
