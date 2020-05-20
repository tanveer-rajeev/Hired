package com.pppfreak.Hired.Entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "admin")
public class Admin {
    @Id
    @GeneratedValue
    private Integer id;
    private String email;
    private String encryptedPassword;

    @Enumerated
    private EmployeeType employeeType;

}
