package com.pppfreak.Hired.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "expertSkill")
public class ExpertSkill {

    @Id
    @GeneratedValue
    private Integer id;
    private String skill;

    @ManyToMany(mappedBy = "expertSkills")
    private Set<CseEmployee> cseEmployeeSet;

    public ExpertSkill() {
    }

    public ExpertSkill(Integer id , String skill) {
        this.id    = id;
        this.skill = skill;
    }
    @JsonBackReference
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

