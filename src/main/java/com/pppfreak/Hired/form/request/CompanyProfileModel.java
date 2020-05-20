package com.pppfreak.Hired.form.request;

import lombok.Data;

@Data
public class CompanyProfileModel {
    private String email;
    private String password;
    private String companyName;
    private String address;
    private String companyActivities;
    private String companyWebsiteLink;

}
