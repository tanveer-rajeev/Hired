package com.pppfreak.Hired.Entity;

import javax.persistence.*;
import java.util.List;

@Entity(name = "textile_employee")
public class TextileEmployee {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, length = 30)
    private String university;

    @Column(nullable = false, length = 2)
    private Integer year_of_experience;

    @Column(nullable = false, length = 30)
    private String expected_Job_Position;

    @Column(nullable = false, length = 5)
    private boolean available_For_Job;

    private String resumeURL;


    public String getExpected_Job_Position() {
        return expected_Job_Position;
    }

    public void setExpected_Job_Position(String expected_Job_Position) {
        this.expected_Job_Position = expected_Job_Position;
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
                ", year_of_experience=" + year_of_experience +
                '}';
    }
}
