package com.pppfreak.Hired.Entity;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "employee")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Employee implements Observer, Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    private String email;
    private String encryptedPassword;
    private String userId;

    @Enumerated
    EmployeeType employeeType;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "jobCategory_id")
    private JobCategory jobCategory;

    @OneToMany(mappedBy = "employee", orphanRemoval = true)
    private List<CseEmployee> cseEmployeeList;

    @ManyToMany
    @JoinTable(name = "employeesJobApplyList", joinColumns = @JoinColumn(name = "employeeId"),
            inverseJoinColumns = @JoinColumn(name = "applyFormId"))
    private List<JobApplyForm> jobApplyForm;

    @ManyToMany
    @JoinTable(name = "notifiedCompany", joinColumns = @JoinColumn(name = "employeeId"),
            inverseJoinColumns = @JoinColumn(name = "companyProfileId"))
    private List<CompanyProfile> notification;

    @ManyToMany
    @JoinTable(name = "subscribedCompanies", joinColumns = @JoinColumn(name = "employeeId"),
            inverseJoinColumns = @JoinColumn(name = "companyProfileId"))
    private List<CompanyProfile> subscribedCompanies;


    @Override
    public void update(CompanyProfile companyProfile) {
        if (notification == null) {
            notification = new ArrayList<>();
        }
        notification.add(companyProfile);
    }
}
