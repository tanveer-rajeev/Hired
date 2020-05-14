package com.pppfreak.Hired.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "jobApplyForm")
public class JobApplyForm implements Serializable {

    @Id
    @GeneratedValue
    private Integer  id;

    private Integer expectedSalary;

    @JsonIgnore
    @ManyToMany(mappedBy = "jobApplyForm")
    private List<Employee> employees;
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "jobCircularId")
    private JobCircular jobCircular;

    public JobCircular getJobCircular() {
        return jobCircular;
    }

    public void setJobCircular(JobCircular jobCircular) {
        this.jobCircular = jobCircular;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getExpectedSalary() {
        return expectedSalary;
    }

    public void setExpectedSalary(Integer expectedSalary) {
        this.expectedSalary = expectedSalary;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
