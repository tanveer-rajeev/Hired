package com.pppfreak.Hired.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
@Entity
public class JobCategory {
    @Id
    @GeneratedValue
    private Integer id;

    private String category;

    @OneToMany(mappedBy = "jobCategory")
    private List<Employee> employees;

    public JobCategory() {
    }

    public JobCategory(Integer id , String category ){
        this.id        = id;
        this.category      = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    @JsonBackReference
    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Employee employees) {
        this.employees.add(employees);
    }
}
