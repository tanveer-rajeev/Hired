package com.pppfreak.Hired.response;

import javax.validation.constraints.Email;

public class EmployeeResponse {
    private String employeeName;
    private String email;
    private String university;
    private String department;
    private String expectedJobPosition;

    public EmployeeResponse() {
      
    }

    public EmployeeResponse(String employeeName, String email, String university, String department, String expectedJobPosition) {
        this.employeeName = employeeName;
        this.email = email;
        this.university = university;
        this.department = department;
        this.expectedJobPosition = expectedJobPosition;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
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

    public String getExpectedJobPosition() {
        return expectedJobPosition;
    }

    public void setExpectedJobPosition(String expectedJobPosition) {
        this.expectedJobPosition = expectedJobPosition;
    }
}
