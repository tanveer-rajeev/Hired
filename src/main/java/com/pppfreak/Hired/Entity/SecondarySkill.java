package com.pppfreak.Hired.Entity;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity(name = "secondarySkill")
public class SecondarySkill implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    private String skill;

    @JsonIgnore
    @ManyToMany(mappedBy = "secondarySkills")
    private Set<CseEmployee> cseEmployees;


}

