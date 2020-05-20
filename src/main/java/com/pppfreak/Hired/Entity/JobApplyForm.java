package com.pppfreak.Hired.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity(name = "jobApplyForm")
public class JobApplyForm implements Serializable {

    @Id
    @GeneratedValue
    private Integer  id;

    private Integer expectedSalary;

    @JsonIgnore
    @ManyToMany(mappedBy = "jobApplyForm")
    private List<Employee> employees;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "jobCircularId")
    private JobCircular jobCircular;

}
