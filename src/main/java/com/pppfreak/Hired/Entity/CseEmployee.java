package com.pppfreak.Hired.Entity;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity(name = "cseEmployee")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class CseEmployee implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "universityId")
    private UniversityBsc universityBsc;

    @ManyToOne
    @JoinColumn(name = "jobFieldId", referencedColumnName = "id")
    private JobField jobField;

     //@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @ManyToMany
    @JoinTable(name = "cseEmployee_expertSkill",
               joinColumns = @JoinColumn(name = "cseEmployee_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "expertSkill_id", referencedColumnName = "id"))
    private Set<ExpertSkill> expertSkills;

     //@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @ManyToMany
    @JoinTable(name = "cseEmployee_secondarySkill",
               joinColumns = @JoinColumn(name = "cseEmployee_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "secondarySkill_id", referencedColumnName = "id"))
    private Set<SecondarySkill> secondarySkills;

    private String yearOfExperience;

    private boolean availableForJob;

    private String resumeURL;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "employeeId", referencedColumnName = "id")
    private Employee employee;



}
