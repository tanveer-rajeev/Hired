package com.pppfreak.Hired.controller;

import com.pppfreak.Hired.Entity.*;
import com.pppfreak.Hired.form.request.ExpertSkillModel;
import com.pppfreak.Hired.form.request.JobFieldModel;
import com.pppfreak.Hired.form.request.SecondarySkillModel;
import com.pppfreak.Hired.form.request.UniversityBscModel;
import com.pppfreak.Hired.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api")
public class AdditionalController {

    private final JobCategoryRepository jobCategoryRepository;
    private  final JobFieldRepository jobFieldRepository;
    private final UniversityBscRepository universityBscRepository;
    private final ExpertSkillRepository expertSkillRepository;
    private final SecondarySkillRepository secondarySkillRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public AdditionalController(JobCategoryRepository jobCategoryRepository , JobFieldRepository jobFieldRepository ,
                                UniversityBscRepository universityBscRepository ,
                                ExpertSkillRepository expertSkillRepository ,
                                SecondarySkillRepository secondarySkillRepository , ModelMapper modelMapper) {
        this.jobCategoryRepository    = jobCategoryRepository;
        this.jobFieldRepository       = jobFieldRepository;
        this.universityBscRepository  = universityBscRepository;
        this.expertSkillRepository    = expertSkillRepository;
        this.secondarySkillRepository = secondarySkillRepository;
        this.modelMapper              = modelMapper;
    }

    @PostMapping("/jobFields")
    public void addJobFields(@RequestBody JobFieldModel jobField){
        JobField temp = modelMapper.map(jobField,JobField.class);
        jobFieldRepository.save(temp);
    }

    @PostMapping(value = "/university",produces = {MediaType.APPLICATION_JSON_VALUE})
    public void addUniversity(@RequestBody UniversityBscModel universityBsc){
        UniversityBsc temp = modelMapper.map(universityBsc,UniversityBsc.class);
        universityBscRepository.save(temp);
    }

    @PostMapping("/jobCategories")
    public void addCategory(@RequestBody JobCategory jobCategory){
        jobCategoryRepository.save(jobCategory);
    }

    @GetMapping("jobCategories/{id}")
    public Optional<JobCategory> getJobCategory(@PathVariable Integer id){
        return jobCategoryRepository.findById(id);
    }

    @PostMapping(value = "/expertSkills",produces = {MediaType.APPLICATION_JSON_VALUE})
    public void addExpertSkill(@RequestBody ExpertSkillModel expertSkill){
        ExpertSkill temp = modelMapper.map(expertSkill,ExpertSkill.class);

        expertSkillRepository.save(temp);
    }

    @PostMapping("/secondarySkills")
    public void addSecondarySkill(@RequestBody SecondarySkillModel secondarySkill){
        SecondarySkill temp = modelMapper.map(secondarySkill,SecondarySkill.class);
        secondarySkillRepository.save(temp);
    }
}
