package com.pppfreak.Hired.controller;

import com.pppfreak.Hired.Entity.*;
import com.pppfreak.Hired.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdditionalController {

    private final JobCategoryRepository jobCategoryRepository;
    private  final JobFieldRepository jobFieldRepository;
    private final UniversityBscRepository universityBscRepository;
    private final ExpertSkillRepository expertSkillRepository;
    private final SecondarySkillRepository secondarySkillRepository;

    @Autowired
    public AdditionalController(JobCategoryRepository jobCategoryRepository , JobFieldRepository jobFieldRepository ,
                                UniversityBscRepository universityBscRepository ,
                                ExpertSkillRepository expertSkillRepository ,
                                SecondarySkillRepository secondarySkillRepository) {
        this.jobCategoryRepository    = jobCategoryRepository;
        this.jobFieldRepository       = jobFieldRepository;
        this.universityBscRepository  = universityBscRepository;
        this.expertSkillRepository    = expertSkillRepository;
        this.secondarySkillRepository = secondarySkillRepository;
    }

    @PostMapping("/jobFields")
    public void addJobFields(@RequestBody JobField jobField){
        jobFieldRepository.save(jobField);
    }

    @PostMapping("/university")
    public void addUniversity(@RequestBody UniversityBsc universityBsc){
        universityBscRepository.save(universityBsc);
    }

    @PostMapping("/jobCategories")
    public void addCategory(@RequestBody JobCategory jobCategory){
        jobCategoryRepository.save(jobCategory);
    }

    @PostMapping("/expertSkills")
    public void addExpertSkill(@RequestBody ExpertSkill expertSkill){
        expertSkillRepository.save(expertSkill);
    }

    @PostMapping("/secondarySkills")
    public void addSecondarySkill(@RequestBody SecondarySkill secondarySkill){
        secondarySkillRepository.save(secondarySkill);
    }
}
