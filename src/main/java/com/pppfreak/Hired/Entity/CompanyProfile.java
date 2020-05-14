package com.pppfreak.Hired.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "companyProfile")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class CompanyProfile implements Subscribe, Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    private String email;
    private String encryptedPassword;
    private String companyName;
    private String address;
    private String companyActivities;
    private String companyWebsiteLink;

    @JsonIgnore
    @OneToMany(mappedBy = "companyProfile")
    private List<JobCircular> jobCircularList;

    @JsonIgnore
    @ManyToMany(mappedBy = "subscribedCompanies")
    private List<Employee> subscriberList;


    @Override
    public void registerObserver(Employee employee) {
        subscriberList.add(employee);

    }

    @Override
    public void removedObserver(Employee employee) {
        if (subscriberList != null && subscriberList.contains(employee)) {
            subscriberList.remove(employee);
        }
    }

    @Override
    public void notifyObserver(CompanyProfile companyProfile) {
        for (Employee  employee:  subscriberList) {
            employee.update(this);
        }
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public List<JobCircular> getJobCircularList() {
        return jobCircularList;
    }

    public void setJobCircularList(List<JobCircular> jobCircularList) {
        this.jobCircularList = jobCircularList;
    }

    public List<Employee> getSubscriberList() {
        return subscriberList;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSubscriberList(List<Employee> subscriberList) {
        this.subscriberList = subscriberList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompanyActivities() {
        return companyActivities;
    }

    public void setCompanyActivities(String companyActivities) {
        this.companyActivities = companyActivities;
    }

    public String getCompanyWebsiteLink() {
        return companyWebsiteLink;
    }

    public void setCompanyWebsiteLink(String companyWebsiteLink) {
        this.companyWebsiteLink = companyWebsiteLink;
    }

}
