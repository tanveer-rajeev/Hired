package com.pppfreak.Hired.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity(name = "employee")
public class Employee {

    @Id
    @GeneratedValue
    private Integer id;
    private String email;
    private String encryptedPassword;
    private String userId;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "jobCategory_id")
    private JobCategory jobCategory;

    @OneToMany(mappedBy = "employee")
    private List<CseEmployee> cseEmployeeList;

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

    public JobCategory getJobCategory() {
        return jobCategory;
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

}
