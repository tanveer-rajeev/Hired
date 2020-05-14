package com.pppfreak.Hired.Entity;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity(name = "expertSkill")
//@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class ExpertSkill implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    private String skill;

    @JsonIgnore
    @ManyToMany(mappedBy = "expertSkills")
    private Set<CseEmployee> cseEmployeeSet;

    public ExpertSkill() {
    }

    public ExpertSkill(Integer id , String skill) {
        this.id    = id;
        this.skill = skill;
    }

    public Set<CseEmployee> getCseEmployeeSet() {
        return cseEmployeeSet;
    }

    public void setCseEmployeeSet(Set<CseEmployee> cseEmployeeSet) {
        this.cseEmployeeSet = cseEmployeeSet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }


}

