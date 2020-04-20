package com.pppfreak.Hired.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "cseEmployee")
public class CseEmployee {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String university;

    private String major_ProgrammingLanguage;

    private String contest_Achievement;

    private String specialised_Technology;

    private String job_Experience;

    private String expected_Job_Position;

    private String resumeURL;


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

    public String getMajor_ProgrammingLanguage() {
        return major_ProgrammingLanguage;
    }

    public void setMajor_ProgrammingLanguage(String major_ProgrammingLanguage) {
        this.major_ProgrammingLanguage = major_ProgrammingLanguage;
    }

    public String getContest_Achievement() {
        return contest_Achievement;
    }

    public void setContest_Achievement(String contest_Achievement) {
        this.contest_Achievement = contest_Achievement;
    }

    public String getSpecialised_Technology() {
        return specialised_Technology;
    }

    public void setSpecialised_Technology(String specialised_Technology) {
        this.specialised_Technology = specialised_Technology;
    }

    public String getJob_Experience() {
        return job_Experience;
    }

    public void setJob_Experience(String job_Experience) {
        this.job_Experience = job_Experience;
    }

    public String getExpected_Job_Position() {
        return expected_Job_Position;
    }

    public void setExpected_Job_Position(String expected_Job_Position) {
        this.expected_Job_Position = expected_Job_Position;
    }

    public String getResumeURL() {
        return resumeURL;
    }

    public void setResumeURL(String resumeURL) {
        this.resumeURL = resumeURL;
    }
}
