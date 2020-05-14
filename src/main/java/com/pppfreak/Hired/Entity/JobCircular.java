package com.pppfreak.Hired.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "jobCircular")
public class JobCircular implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "jobCategoryId")
    private JobCategory jobCategory;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "companyProfileId")
    private CompanyProfile companyProfile;
    @JsonIgnore
    @OneToOne(mappedBy = "jobCircular")
    private JobApplyForm jobApplyForm;

    private String jobTitle;
    private Integer vacancy;
    private String jobResponsibility;
    private String  employmentStatus;
    private String educationRequirements;
    private String experienceRequirements;
    private String additionalRequirements;
    private String jobLocation;
    private Integer salary;
    private String compensationOtherBenefits;
    private String applicationDeadline;
    private boolean enable;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public JobCategory getJobCategory() {
        return jobCategory;
    }

    public void setJobCategory(JobCategory jobCategory) {
        this.jobCategory = jobCategory;
    }

    public CompanyProfile getCompanyProfile() {
        return companyProfile;
    }

    public void setCompanyProfile(CompanyProfile companyProfile) {
        this.companyProfile = companyProfile;
    }

    public JobApplyForm getJobApplyForm() {
        return jobApplyForm;
    }

    public void setJobApplyForm(JobApplyForm jobApplyForm) {
        this.jobApplyForm = jobApplyForm;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Integer getVacancy() {
        return vacancy;
    }

    public void setVacancy(Integer vacancy) {
        this.vacancy = vacancy;
    }

    public String getJobResponsibility() {
        return jobResponsibility;
    }

    public void setJobResponsibility(String jobResponsibility) {
        this.jobResponsibility = jobResponsibility;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(String employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public String getEducationRequirements() {
        return educationRequirements;
    }

    public void setEducationRequirements(String educationRequirements) {
        this.educationRequirements = educationRequirements;
    }

    public String getExperienceRequirements() {
        return experienceRequirements;
    }

    public void setExperienceRequirements(String experienceRequirements) {
        this.experienceRequirements = experienceRequirements;
    }

    public String getAdditionalRequirements() {
        return additionalRequirements;
    }

    public void setAdditionalRequirements(String additionalRequirements) {
        this.additionalRequirements = additionalRequirements;
    }

    public String getJobLocation() {
        return jobLocation;
    }

    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getCompensationOtherBenefits() {
        return compensationOtherBenefits;
    }

    public void setCompensationOtherBenefits(String compensationOtherBenefits) {
        this.compensationOtherBenefits = compensationOtherBenefits;
    }

    public String getApplicationDeadline() {
        return applicationDeadline;
    }

    public void setApplicationDeadline(String applicationDeadline) {
        this.applicationDeadline = applicationDeadline;
    }
}
