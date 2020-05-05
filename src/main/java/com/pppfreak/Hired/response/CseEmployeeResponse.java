package com.pppfreak.Hired.response;

import com.pppfreak.Hired.Entity.*;

import java.util.Set;

public class CseEmployeeResponse {
    private String name;

    private String university;

    private UniversityBsc universityBsc;

    private JobField jobField;

    private Set<ExpertSkill> expertSkills;

    private Set<SecondarySkill> secondarySkills;

    private String yearOfExperience;

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

    public UniversityBsc getUniversityBsc() {
        return universityBsc;
    }

    public void setUniversityBsc(UniversityBsc universityBsc) {
        this.universityBsc = universityBsc;
    }

    public JobField getJobField() {
        return jobField;
    }

    public void setJobField(JobField jobField) {
        this.jobField = jobField;
    }

    public Set<ExpertSkill> getExpertSkills() {
        return expertSkills;
    }

    public void setExpertSkills(Set<ExpertSkill> expertSkills) {
        this.expertSkills = expertSkills;
    }

    public Set<SecondarySkill> getSecondarySkills() {
        return secondarySkills;
    }

    public void setSecondarySkills(Set<SecondarySkill> secondarySkills) {
        this.secondarySkills = secondarySkills;
    }

    public String getYearOfExperience() {
        return yearOfExperience;
    }

    public void setYearOfExperience(String yearOfExperience) {
        this.yearOfExperience = yearOfExperience;
    }
}
