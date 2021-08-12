package com.pppfreak.Hired.Entity;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
@Data
@Entity(name = "jobField")
public class JobField implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    private String field;

    @JsonIgnore
    @OneToMany(mappedBy = "jobField")
    private List<CseEmployee> cseEmployees;


}
