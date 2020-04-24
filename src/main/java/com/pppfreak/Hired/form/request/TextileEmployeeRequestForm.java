package com.pppfreak.Hired.form.request;

public class TextileEmployeeRequestForm {

    private String name;
    private String university;
    private Integer year_of_experience;
    private String expected_Job_Position;
    private boolean availableForJob;
    private String resumeURL;



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

    public boolean isAvailableForJob() {
        return availableForJob;
    }

    public void setAvailableForJob(boolean availableForJob) {
        this.availableForJob = availableForJob;
    }

}
