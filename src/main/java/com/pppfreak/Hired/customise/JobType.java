package com.pppfreak.Hired.customise;


public enum JobType {

    CSE("Computer Engineering"),
    IT("Information Technology"),

    TEXTILE("Textile Engineering"),
    CIVIL("Civil engineering"),
    EEE("Electrical and Electronics Engineering");
    private final String department;

    JobType(String department) {
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }
}
