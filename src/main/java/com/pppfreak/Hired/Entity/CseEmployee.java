package com.pppfreak.Hired.Entity;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity(name = "cseEmployee")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class CseEmployee implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "universityId")
    private UniversityBsc universityBsc;

    @ManyToOne
    @JoinColumn(name = "jobFieldId", referencedColumnName = "id")
    private JobField jobField;

     //@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @ManyToMany
    @JoinTable(name = "cseEmployee_expertSkill",
               joinColumns = @JoinColumn(name = "cseEmployee_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "expertSkill_id", referencedColumnName = "id"))
    private Set<ExpertSkill> expertSkills;

     //@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @ManyToMany
    @JoinTable(name = "cseEmployee_secondarySkill",
               joinColumns = @JoinColumn(name = "cseEmployee_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "secondarySkill_id", referencedColumnName = "id"))
    private Set<SecondarySkill> secondarySkills;

    private String yearOfExperience;

    private boolean availableForJob;

    private String resumeURL;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "employeeId", referencedColumnName = "id")
    private Employee employee;

    public boolean isAvailableForJob() {
        return availableForJob;
    }

    public void setAvailableForJob(boolean availableForJob) {
        this.availableForJob = availableForJob;
    }

    public UniversityBsc getUniversityBsc() {
        return universityBsc;
    }

    public void setUniversityBsc(UniversityBsc universityBsc) {
        this.universityBsc = universityBsc;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setSecondarySkills(Set<SecondarySkill> secondarySkills) {
        this.secondarySkills = secondarySkills;
    }

    public void setExpertSkills(Set<ExpertSkill> expertSkills) {
        this.expertSkills = expertSkills;
    }

    public void setJobField(JobField jobField) {
        this.jobField = jobField;
    }

    public String getYearOfExperience() {
        return yearOfExperience;
    }

    public void setYearOfExperience(String yearOfExperience) {
        this.yearOfExperience = yearOfExperience;
    }

    public String getResumeURL() {
        return resumeURL;
    }

    public void setResumeURL(String resumeURL) {
        this.resumeURL = resumeURL;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Set<SecondarySkill> getSecondarySkills() {
        return secondarySkills;
    }

    public Set<ExpertSkill> getExpertSkills() {
        return expertSkills;
    }

    public JobField getJobField() {
        return jobField;
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


}
