package com.pppfreak.Hired.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity(name = "companyProfile")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class CompanyProfile implements Subscribe, Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    private String email;
    private String encryptedPassword;
    private String companyName;
    private String address;
    private String companyActivities;
    private String companyWebsiteLink;

    @JsonIgnore
    @OneToMany(mappedBy = "companyProfile")
    private List<JobCircular> jobCircularList;

    @JsonIgnore
    @ManyToMany(mappedBy = "subscribedCompanies")
    private List<Employee> subscriberList;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "unsubscribe_list", joinColumns = @JoinColumn(name = "companyProfileId")
            , inverseJoinColumns = @JoinColumn(name = "employeeId"))
    private List<Employee> unsubscribeList;

    @Override
    public void registerObserver(Employee employee) {
        subscriberList.add(employee);

    }

    @Override
    public void removedObserver(Employee employee) {
        if (subscriberList != null) {
            subscriberList.remove(employee);
        }
    }

    @Override
    public void notifyObserver(CompanyProfile companyProfile) {
        for (Employee employee : subscriberList) {
            if (!unsubscribeList.contains(employee)) {
                employee.update(this);
            }

        }
    }


}
