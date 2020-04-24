package com.pppfreak.Hired.Entity;

import javax.persistence.*;

@Entity(name = "cseEmployee")
public class CseEmployee {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String university;

    @Column(nullable = false)
    private String major_ProgrammingLanguage;

    @Column(nullable = false)
    private String contest_Achievement;

    @Column(nullable = false)
    private String specialised_Technology;

    @Column(nullable = false)
    private String job_Experience;

    @Column(nullable = false)
    private String expected_Job_Position;

    @Column(nullable = false)
    private String available_For_Job;

    private String resumeURL;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public CseEmployee() {
    }

    public CseEmployee(Integer id , String name , String university , String major_ProgrammingLanguage ,
                       String contest_Achievement , String specialised_Technology , String job_Experience ,
                       String expected_Job_Position , String available_For_Job , String resumeURL , Integer employee_id) {
        this.id                        = id;
        this.name                      = name;
        this.university                = university;
        this.major_ProgrammingLanguage = major_ProgrammingLanguage;
        this.contest_Achievement       = contest_Achievement;
        this.specialised_Technology    = specialised_Technology;
        this.job_Experience            = job_Experience;
        this.expected_Job_Position     = expected_Job_Position;
        this.available_For_Job         = available_For_Job;
        this.resumeURL                 = resumeURL;
        this.employee                  = new Employee(employee_id, "" , "" , "");
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getAvailable_For_Job() {
        return available_For_Job;
    }

    public void setAvailable_For_Job(String available_For_Job) {
        this.available_For_Job = available_For_Job;
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
