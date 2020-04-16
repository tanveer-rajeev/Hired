package com.pppfreak.Hired.request_response_Model;

import com.pppfreak.Hired.Entity.Experience;

import java.util.List;

public class EmployeeRequestModel {

    private String name;
    private String email;
    private String password;
    private String university;
    private String department;
    private Integer year_of_experience;
    private String expectedJobPosition;
    private String resumeURL;
    private boolean availableForJob;
    List<Experience> experienceList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getExpectedJobPosition() {
        return expectedJobPosition;
    }

    public void setExpectedJobPosition(String expectedJobPosition) {
        this.expectedJobPosition = expectedJobPosition;
    }

    public String getResumeURL() {
        return resumeURL;
    }

    public void setResumeURL(String resumeURL) {
        this.resumeURL = resumeURL;
    }

    public boolean isAvailableForJob() {
        return availableForJob;
    }

    public void setAvailableForJob(boolean availableForJob) {
        this.availableForJob = availableForJob;
    }

    public List<Experience> getExperienceList() {
        return experienceList;
    }

    public void setExperienceList(List<Experience> experienceList) {
        this.experienceList = experienceList;
    }
}
