package com.pppfreak.Hired.Entity;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity(name = "university")
public class UniversityBsc implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    private String universityName;
    @JsonIgnore
    @OneToMany(mappedBy = "universityBsc")
    private Set<CseEmployee> cseEmployee;

    @JsonBackReference
    public Set<CseEmployee> getCseEmployee() {
        return cseEmployee;
    }

    public void setCseEmployee(Set<CseEmployee> cseEmployee) {
        this.cseEmployee = cseEmployee;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }
}
