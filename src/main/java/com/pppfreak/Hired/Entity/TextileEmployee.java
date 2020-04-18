package com.pppfreak.Hired.Entity;

import javax.persistence.*;
import java.util.List;

@Entity(name = "employee")
public class TextileEmployee {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false,length = 40)
    private String  userId;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 250)
    private String encryptedPassword;

    @Column(nullable = false, length = 30)
    private String university;

    @Column(nullable = false, length = 30)
    private String department;

    @Column(nullable = false, length = 2)
    private Integer year_of_experience;

    @Column(nullable = false, length = 30)
    private String expected_Job_Role;

    private String resumeURL;

    @Column(nullable = false, length = 5)
    private boolean available_For_Job;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "office_experience_id")
    List<Experience> experienceList;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getExpected_Job_Role() {
        return expected_Job_Role;
    }

    public void setExpected_Job_Role(String expected_Job_Role) {
        this.expected_Job_Role = expected_Job_Role;
    }

    public boolean isAvailable_For_Job() {
        return available_For_Job;
    }

    public void setAvailable_For_Job(boolean available_For_Job) {
        this.available_For_Job = available_For_Job;
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
