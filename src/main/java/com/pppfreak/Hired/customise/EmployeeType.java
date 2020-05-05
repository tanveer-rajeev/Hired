package com.pppfreak.Hired.customise;


public enum  EmployeeType {

    CSE("Computer Engineering"),
    IT("Information Technology"),

    TEXTILE("Textile Engineering"),
    CIVIL("Civil engineering"),
    EEE("Electrical and Electronics Engineering");
     private final String department;

    EmployeeType(String department) {
        this.department = department;
    }

    public  String getDepartment() {
        return department;
    }
}
