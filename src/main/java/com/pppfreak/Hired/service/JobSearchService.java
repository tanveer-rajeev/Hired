package com.pppfreak.Hired.service;

import com.pppfreak.Hired.response.JobCircularResponse;

import java.util.List;

public interface JobSearchService {

    JobCircularResponse getJobById(Integer id);

    List<JobCircularResponse> getAllJobsByJobCategory(Integer id);

    List<JobCircularResponse> getAllJobsByLocation(String location);

    List<JobCircularResponse> getAllJobsBySkillKeyword(String skill);

    List<JobCircularResponse> getAllJobsByJobTitle(String jobTitle);

    String deleteJobCircular(Integer id);

}
