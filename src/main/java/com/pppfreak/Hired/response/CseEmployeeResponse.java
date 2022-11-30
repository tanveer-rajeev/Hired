package com.pppfreak.Hired.response;

import com.pppfreak.Hired.Entity.*;
import lombok.Data;

import java.util.Set;

@Data
public class CseEmployeeResponse {
    private String name;

    private String university;

    private UniversityBsc universityBsc;

    private JobField jobField;

    private Set<ExpertSkill> expertSkills;

    private Set<SecondarySkill> secondarySkills;

    private String yearOfExperience;


}
