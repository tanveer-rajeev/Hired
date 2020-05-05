package com.pppfreak.Hired.form.request;

import com.pppfreak.Hired.Entity.JobCategory;
import com.pppfreak.Hired.Entity.JobField;

public class EmployeeRequestForm {
    private String email;
    private String password;
    private JobCategory jobCategory;

    public JobCategory getJobCategory() {
        return jobCategory;
    }

    public void setJobCategory(JobCategory jobCategory) {
        this.jobCategory = jobCategory;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
