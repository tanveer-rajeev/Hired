package com.pppfreak.Hired.controller;

import com.pppfreak.Hired.response.JobCircularResponse;
import com.pppfreak.Hired.service.JobSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobSearchingController {

    private final JobSearchService jobSearchService;

    @Autowired
    public JobSearchingController(JobSearchService jobSearchService) {
        this.jobSearchService = jobSearchService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('employee:read')")
    public JobCircularResponse getAllJobsCircular(@PathVariable Integer id){
        return jobSearchService.getJobById(id);
    }

    @GetMapping("findByCategory/{id}")
    public List<JobCircularResponse> findAllJobsCircularByJobCategory(@PathVariable Integer id){
        return jobSearchService.getAllJobsByJobCategory(id);
    }

    @GetMapping("findByJobTitle/{title}")
    public List<JobCircularResponse> findAllJobsByTitle(@PathVariable String title){
        return  jobSearchService.getAllJobsByJobTitle(title);
    }

    @GetMapping("findByLocation/{location}")
    public List<JobCircularResponse> findAllJobsByLocation(@PathVariable String location){
        return jobSearchService.getAllJobsByLocation(location);
    }

    @GetMapping("findBySkill/{skillKeyword}")
    public List<JobCircularResponse> findAllJobsBySkillKeyword(@PathVariable String skillKeyword){
        return jobSearchService.getAllJobsBySkillKeyword(skillKeyword);
    }





}
