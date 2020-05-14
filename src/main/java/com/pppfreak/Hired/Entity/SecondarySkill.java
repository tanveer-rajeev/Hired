package com.pppfreak.Hired.Entity;
import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity(name = "secondarySkill")
public class SecondarySkill implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    private String skill;

    @JsonIgnore
    @ManyToMany(mappedBy = "secondarySkills")
    private Set<CseEmployee> cseEmployees;

    public SecondarySkill() {
    }

    public SecondarySkill(Integer id , String skill) {
        this.id    = id;
        this.skill = skill;
    }

    public Set<CseEmployee> getCseEmployees() {
        return cseEmployees;
    }

    public void setCseEmployees(Set<CseEmployee> cseEmployees) {
        this.cseEmployees = cseEmployees;
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

