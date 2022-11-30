package com.pppfreak.Hired.form.request;

import com.pppfreak.Hired.Entity.CompanyJobTitle;
import com.pppfreak.Hired.Entity.JobCategory;
import lombok.Data;

@Data
public class JobCircularForm {
    private JobCategory jobCategory;
    private CompanyJobTitle companyJobTitle;
    private Integer vacancy;
    private String jobResponsibility;
    private String employmentStatus;
    private String educationRequirements;
    private String experienceRequirements;
    private String additionalRequirements;
    private String jobLocation;
    private Integer salary;
    private String compensationOtherBenefits;
    private String applicationDeadline;
    private boolean enable;


}
