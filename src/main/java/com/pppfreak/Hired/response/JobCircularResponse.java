package com.pppfreak.Hired.response;

import com.pppfreak.Hired.Entity.CompanyJobTitle;
import com.pppfreak.Hired.Entity.JobCategory;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Data
public class JobCircularResponse {
    private JobCategory jobCategory;
    private CompanyJobTitle jobTitle;
    private Integer vacancy;
    private String jobResponsibility;
    private String  employmentStatus;
    private String educationRequirements;
    private String experienceRequirements;
    private String additionalRequirements;
    private String jobLocation;
    private Integer salary;
    private String compensationOtherBenefits;
    private String applicationDeadline;
    private boolean enable;


    public List<String> getJobResponsibility() {
        String[] arr = jobResponsibility.split("[.]");
        return new ArrayList<>(Arrays.asList(arr));
    }

    public List<String> getAdditionalRequirements() {
        String[] arr = additionalRequirements.split("[.]");
        return new ArrayList<>(Arrays.asList(arr));
    }


}
