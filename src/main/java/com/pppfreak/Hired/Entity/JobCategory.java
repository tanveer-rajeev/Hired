package com.pppfreak.Hired.Entity;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
@Entity(name = "jobCategory")
public class JobCategory implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;

    private String category;
    @JsonIgnore
    @OneToMany(mappedBy = "jobCategory")
    private List<Employee> employees;

    @JsonIgnore
    @OneToMany(mappedBy = "jobCategory")
    private List<JobCircular> jobCirculars;


    public JobCategory() {
    }

    public JobCategory(Integer id , String category ){
        this.id        = id;
        this.category      = category;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<JobCircular> getJobCirculars() {
        return jobCirculars;
    }

    public List<Employee> getEmployees() {
        return employees;
    }
    public void setJobCirculars(List<JobCircular> jobCirculars) {
        this.jobCirculars = jobCirculars;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }



}
