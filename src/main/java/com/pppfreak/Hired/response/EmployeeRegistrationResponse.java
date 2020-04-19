package com.pppfreak.Hired.response;

public class EmployeeRegistrationResponse {

    private String email;
    private String dept;

    public EmployeeRegistrationResponse() {
    }

    public EmployeeRegistrationResponse(String email , String dept) {
        this.email = email;
        this.dept  = dept;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }
}
