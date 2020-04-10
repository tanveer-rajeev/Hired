package com.pppfreak.Hired.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "experience")
public class Experience {

    @Id
    @GeneratedValue
    Integer id;

    @Column(nullable = false,length = 30)
    private String companyName;

    @Column(nullable = false,length = 100)
    private String role;

    @Column(nullable = false,length = 100)
    private String responsibility;

    @Column(nullable = false,length = 30)
    private  String jobTimeLength;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJobTimeLength() {
        return jobTimeLength;
    }

    public void setJobTimeLength(String jobTimeLength) {
        this.jobTimeLength = jobTimeLength;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getResponsibility() {
        return responsibility;
    }

    public void setResponsibility(String responsibility) {
        this.responsibility = responsibility;
    }

    @Override
    public String toString() {
        return "Experience{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", role='" + role + '\'' +
                ", responsibility='" + responsibility + '\'' +
                ", jobTimeLength='" + jobTimeLength + '\'' +
                '}';
    }
}
