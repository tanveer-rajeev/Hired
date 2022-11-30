package com.pppfreak.Hired.Entity;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
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

}
