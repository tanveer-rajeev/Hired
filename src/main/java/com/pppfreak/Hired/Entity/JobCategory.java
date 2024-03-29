package com.pppfreak.Hired.Entity;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "jobCategory")
public class JobCategory implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;

    private String category;
    @JsonIgnore
    @OneToMany(mappedBy = "jobCategory")
    private List<Employee> employees;

    @JsonIgnore
    @OneToMany(mappedBy = "jobCategory")
    private List<JobCircular> jobCirculars;

}
