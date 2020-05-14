package com.pppfreak.Hired.Entity;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "employee")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Employee implements Observer, Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    private String email;
    private String encryptedPassword;
    private String userId;

    @Enumerated
    EmployeeType employeeType;


    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "jobCategory_id")
    private JobCategory jobCategory;


    @OneToMany(mappedBy = "employee")
    private List<CseEmployee> cseEmployeeList;

    @ManyToMany
    @JoinTable(name = "employeesJobApplyList", joinColumns = @JoinColumn(name = "employeeId"),
               inverseJoinColumns = @JoinColumn(name = "applyFormId"))
    private List<JobApplyForm> jobApplyForm;

    @ManyToMany
    @JoinTable(name = "notifiedCompany", joinColumns = @JoinColumn(name = "employeeId"),
               inverseJoinColumns = @JoinColumn(name = "companyProfileId"))
    private List<CompanyProfile> notification;

    @ManyToMany
    @JoinTable(name = "subscribedCompanies", joinColumns = @JoinColumn(name = "employeeId"),
               inverseJoinColumns = @JoinColumn(name = "companyProfileId"))
    private List<CompanyProfile> subscribedCompanies;


    public List<CompanyProfile> getNotification() {
        return notification;
    }

    public List<CompanyProfile> getSubscribedCompanies() {
        return subscribedCompanies;
    }

    public List<JobApplyForm> getJobApplyForm() {
        return jobApplyForm;
    }

    public JobCategory getJobCategory() {
        return jobCategory;
    }

    public void setSubscribedCompanies(List<CompanyProfile> subscribedCompanies) {
        this.subscribedCompanies = subscribedCompanies;
    }

    public void setNotification(List<CompanyProfile> notification) {
        this.notification = notification;
    }

    public void setJobApplyForm(List<JobApplyForm> jobApplyForm) {
        this.jobApplyForm = jobApplyForm;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

    public List<CseEmployee> getCseEmployeeList() {
        return cseEmployeeList;
    }

    public void setCseEmployeeList(List<CseEmployee> cseEmployeeList) {
        this.cseEmployeeList = cseEmployeeList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public void setJobCategory(JobCategory jobCategory) {
        this.jobCategory = jobCategory;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    @Override
    public void update(CompanyProfile companyProfile) {
        if (notification == null) {
            notification = new ArrayList<>();
        }
        notification.add(companyProfile);
    }
}
