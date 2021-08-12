package com.pppfreak.Hired.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.util.List;
@Data
@Entity(name = "companiesJobTitle")
public class CompanyJobTitle {

    @Id
    @GeneratedValue
    private Integer id;

    private String jobTitle;

    @JsonIgnore
    @OneToMany(mappedBy = "companyJobTitle")
    private List<JobCircular> jobCirculars;


}
