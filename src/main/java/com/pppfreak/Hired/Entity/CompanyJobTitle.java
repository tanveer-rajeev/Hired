package com.pppfreak.Hired.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.util.List;

@Entity(name = "companiesJobTitle")
public class CompanyJobTitle {

    @Id
    @GeneratedValue
    private Integer id;

    private String jobTitle;

    @JsonIgnore
    @OneToMany(mappedBy = "companyJobTitle")
    private List<JobCircular> jobCirculars;

    public List<JobCircular> getJobCirculars() {
        return jobCirculars;
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

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

}
