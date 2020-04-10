package com.pppfreak.Hired.Entity;

public class Response {
    private String employeeName;
    private String university;
    private String department;
    private String fileName;

    public Response(String employeeName, String university, String department, String fileName) {
        this.employeeName = employeeName;
        this.university = university;
        this.department = department;
        this.fileName = fileName;
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
