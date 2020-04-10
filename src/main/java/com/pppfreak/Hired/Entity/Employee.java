package com.pppfreak.Hired.Entity;

import javax.persistence.*;
import java.util.List;

@Entity(name = "employee")
public class Employee {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, length = 30)
    private String university;

    @Column(nullable = false, length = 30)
    private String department;

    @Column(nullable = false, length = 2)
    private Integer year_of_experience;

    @Column(nullable = false,length = 30)
    private String expectedJobPosition;

    private String resumeURL;

    @Column(nullable = false,length = 5)
    private boolean availableForJob;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "office_experience_id")
    List<Experience> experienceList ;

    Employee(){

    }

    public Employee(String name, String university, String department, Integer year_of_experience, String resumeURL, List<Experience> experienceList) {
        this.name = name;
        this.university = university;
        this.department = department;
        this.year_of_experience = year_of_experience;
        this.resumeURL= resumeURL;
        this.experienceList = experienceList;
    }


    public String getExpectedJobPosition() {
        return expectedJobPosition;
    }

    public void setExpectedJobPosition(String expectedJobPosition) {
        this.expectedJobPosition = expectedJobPosition;
    }

    public boolean isAvailableForJob() {
        return availableForJob;
    }

    public void setAvailableForJob(boolean availableForJob) {
        this.availableForJob = availableForJob;
    }

    public String getResumeURL() {
        return resumeURL;
    }

    public void setResumeURL(String resumeURL) {
        this.resumeURL = resumeURL;
    }
    public List<Experience> getExperienceList() {
        return experienceList;
    }

    public void setExperienceList(List<Experience> experienceList) {
        this.experienceList = experienceList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getYear_of_experience() {
        return year_of_experience;
    }

    public void setYear_of_experience(Integer year_of_experience) {
        this.year_of_experience = year_of_experience;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", university='" + university + '\'' +
                ", department='" + department + '\'' +
                ", year_of_experience=" + year_of_experience +
                ", experienceList=" + experienceList +
                '}';
    }
}
