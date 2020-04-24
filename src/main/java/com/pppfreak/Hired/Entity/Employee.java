package com.pppfreak.Hired.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder;

@Entity(name = "employee")
public class Employee {

    @Id
    @GeneratedValue
    private Integer id;
    private String userId;
    private String email;
    private String encryptedPassword;

    public Employee() {
    }

    public Employee(Integer id, String userId , String email , String encryptedPassword) {
        this.id               =id;
        this.userId            = userId;
        this.email             = email;
        this.encryptedPassword = encryptedPassword;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

}
