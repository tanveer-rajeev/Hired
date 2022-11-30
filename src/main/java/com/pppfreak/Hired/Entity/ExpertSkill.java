package com.pppfreak.Hired.Entity;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity(name = "expertSkill")
public class ExpertSkill implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    private String skill;

    @JsonIgnore
    @ManyToMany(mappedBy = "expertSkills")
    private Set<CseEmployee> cseEmployeeSet;


}

