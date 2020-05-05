package com.pppfreak.Hired.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity(name = "jobField")
public class JobField {

    @Id
    @GeneratedValue
    private Integer id;

    private String field;

    @OneToMany(mappedBy = "jobField")
    private List<CseEmployee> cseEmployees;

    public JobField() {
    }

    public JobField(Integer id , String field) {
        this.id    = id;
        this.field = field;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    @JsonBackReference
    public List<CseEmployee> getCseEmployees() {
        return cseEmployees;
    }

    public void setCseEmployees(List<CseEmployee> cseEmployees) {
        this.cseEmployees = cseEmployees;
    }
}
