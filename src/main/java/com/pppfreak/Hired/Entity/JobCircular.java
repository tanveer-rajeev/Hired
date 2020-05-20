package com.pppfreak.Hired.Entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
@Data
@Entity(name = "jobCircular")
public class JobCircular implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "jobCategoryId")
    private JobCategory jobCategory;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "companyProfileId")
    private CompanyProfile companyProfile;

    @JsonIgnore
    @OneToOne(mappedBy = "jobCircular")
    private JobApplyForm jobApplyForm;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "companyJobTitleId")
    private CompanyJobTitle companyJobTitle;

    private Integer vacancy;
    private String jobResponsibility;
    private String  employmentStatus;
    private String educationRequirements;
    private String experienceRequirements;
    private String additionalRequirements;
    private String jobLocation;
    private Integer salary;
    private String compensationOtherBenefits;
    @JsonFormat(pattern = "yyyy-MM-dd",shape = JsonFormat.Shape.STRING)
    private String applicationDeadline;
    private boolean enable;

}
