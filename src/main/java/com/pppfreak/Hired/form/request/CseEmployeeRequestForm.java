package com.pppfreak.Hired.form.request;

import com.pppfreak.Hired.Entity.ExpertSkill;
import com.pppfreak.Hired.Entity.JobField;
import com.pppfreak.Hired.Entity.SecondarySkill;
import com.pppfreak.Hired.Entity.UniversityBsc;
import lombok.Data;

import java.util.Set;

@Data
public class CseEmployeeRequestForm {

    private String name;

    private UniversityBsc universityBsc;

    private JobField jobField;

    private Set<ExpertSkill> expertSkills;

    private Set<SecondarySkill> secondarySkills;

    private String yearOfExperience;

    private String availableForJob;

}
