package com.pppfreak.Hired.Entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity(name = "secondarySkill")
public class SecondarySkill {

    @Id
    @GeneratedValue
    private Integer id;

    private String skill;

    @ManyToMany(mappedBy = "secondarySkills")
    private Set<CseEmployee> cseEmployees;

    public SecondarySkill() {
    }

    public SecondarySkill(Integer id , String skill) {
        this.id    = id;
        this.skill = skill;
    }
    @JsonBackReference
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

