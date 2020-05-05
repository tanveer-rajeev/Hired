package com.pppfreak.Hired.controller;

import com.pppfreak.Hired.Entity.*;
import com.pppfreak.Hired.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/findBy")
public class FindEmployeeController {

    private final ExpertSkillRepository expertSkillRepository;
    private final SecondarySkillRepository secondarySkillRepository;
    private final UniversityBscRepository universityBscRepository;
    private final JobCategoryRepository jobCategoryRepository;
    private final JobFieldRepository jobFieldRepository;

    @Autowired
    public FindEmployeeController(ExpertSkillRepository expertSkillRepository ,
                                  SecondarySkillRepository secondarySkillRepository ,
                                  UniversityBscRepository universityBscRepository ,
                                  JobCategoryRepository jobCategoryRepository , JobFieldRepository jobFieldRepository) {
        this.expertSkillRepository    = expertSkillRepository;
        this.secondarySkillRepository = secondarySkillRepository;
        this.universityBscRepository  = universityBscRepository;
        this.jobCategoryRepository    = jobCategoryRepository;
        this.jobFieldRepository       = jobFieldRepository;
    }


    @GetMapping("expertSkill/{skill}")
    public Set<CseEmployee> findByExpertSkill(@PathVariable String skill){
        ExpertSkill expertSkill = expertSkillRepository.findBySkill(skill);
        return expertSkill.getCseEmployeeSet();
    }

    @GetMapping("secondarySkill/{skill}")
    public Set<CseEmployee> findBySecondarySkill(@PathVariable String skill){
        return secondarySkillRepository.findBySkill(skill).getCseEmployees();

    }

    @GetMapping("jobField/{field}")
    public List<CseEmployee> findByJobField(@PathVariable String field){
        return jobFieldRepository.findByField(field).getCseEmployees();
    }

    @GetMapping("universityBsc/{university}")
    public Set<CseEmployee> findByUniversityBsc(@PathVariable String university){
        return universityBscRepository.findByUniversityName(university).getCseEmployee();
    }

    @GetMapping("jobCategory/{category}")
    public List<CseEmployee> findByJobCategory(@PathVariable String category){
        JobCategory jobCategory=jobCategoryRepository.findByCategory(category);
        List<Employee> employee = jobCategory.getEmployees();

        return employee.stream()
                .map(Employee::getCseEmployeeList)
                .collect(Collectors.toList())
                .get(0);
    }
}
