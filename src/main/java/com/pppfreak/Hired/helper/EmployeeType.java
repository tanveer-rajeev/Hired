package com.pppfreak.Hired.helper;


public enum  EmployeeType {

    CSE("cse"),
    IT("it"),
    TEXTILE("textile"),
    CIVIL("civil"),
    EEE("eee");
     private final String department;

    EmployeeType(String department) {
        this.department = department;
    }

    public  String getDepartment() {
        return department;
    }
}
