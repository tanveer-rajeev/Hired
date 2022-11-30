package com.pppfreak.Hired.Entity;

import javax.persistence.Entity;

public enum EmployeeType {

    EMPLOYEE(1),
    COMPANY(2),
    ADMIN(3);

    private Integer numbering;

    EmployeeType(Integer numbering) {
        this.numbering = numbering;
    }

    public Integer getNumbering() {
        return numbering;
    }
}
