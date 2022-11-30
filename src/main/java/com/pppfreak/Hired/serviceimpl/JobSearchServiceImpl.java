package com.pppfreak.Hired.serviceimpl;

import com.pppfreak.Hired.Entity.JobCategory;
import com.pppfreak.Hired.Entity.JobCircular;
import com.pppfreak.Hired.customise.MassageConstant;
import com.pppfreak.Hired.repository.*;
import com.pppfreak.Hired.response.JobCircularResponse;
import com.pppfreak.Hired.service.JobSearchService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class JobSearchServiceImpl implements JobSearchService {

    private final JobCircularRepository jobCircularRepository;
    private final JobCategoryRepository jobCategoryRepository;
    private final JobFieldRepository jobFieldRepository;
    private final ModelMapper modelMapper;
    private final CompanyJobTitleRepository companyJobTitleRepository;
    private final JobApplyRepository jobApplyRepository;

    @Autowired
    public JobSearchServiceImpl(JobCircularRepository jobCircularRepository, JobCategoryRepository jobCategoryRepository,
                                JobFieldRepository jobFieldRepository, ModelMapper modelMapper,
                                CompanyJobTitleRepository companyJobTitleRepository,
                                JobApplyRepository jobApplyRepository) {
        this.jobCircularRepository = jobCircularRepository;
        this.jobCategoryRepository = jobCategoryRepository;
        this.jobFieldRepository = jobFieldRepository;
        this.modelMapper = modelMapper;
        this.companyJobTitleRepository = companyJobTitleRepository;
        this.jobApplyRepository = jobApplyRepository;
    }

    @Override
    public JobCircularResponse getJobById(Integer id) {

        return jobCircularRepository.findById(id)
                .stream()
                .filter(jobCircular1 -> jobCircular1.getId().equals(id))
                .map(jobCircular -> modelMapper.map(jobCircular, JobCircularResponse.class))
                .findFirst().orElseThrow(() -> new RuntimeException("job circular not found" + id));

    }

    @Override
    public List<JobCircularResponse> getAllJobsByJobTitle(String jobTitle) {
        return companyJobTitleRepository.findByJobTitle(jobTitle)
                .getJobCirculars()
                .stream()
                .map(jobCircular -> modelMapper.map(jobCircular, JobCircularResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public String deleteJobCircular(Integer id) {
        jobCircularRepository.deleteById(id);
        return MassageConstant.SUCCESS.name();
    }


    @Override
    public List<JobCircularResponse> getAllJobsByJobCategory(Integer id) {
        JobCategory jobCategory = jobCategoryRepository.findById(id).stream()
                .filter(jobCategory1 -> jobCategory1.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Job category not found" + id));

        return jobCategory.getJobCirculars()
                .stream()
                .map(jobCircular -> modelMapper.map(jobCircular, JobCircularResponse.class))
                .collect(Collectors.toList());

    }

    @Override
    public List<JobCircularResponse> getAllJobsByLocation(String location) {

        return jobCircularRepository.findByJobLocation(location)
                .stream()
                .map(jobCircular -> modelMapper.map(jobCircular, JobCircularResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<JobCircularResponse> getAllJobsBySkillKeyword(String skill) {
        List<JobCircular> jobCircularList = new ArrayList<>();
        List<JobCircularResponse> jobCircularResponses = new ArrayList<>();

        jobCircularRepository.findAll().forEach(jobCircularList::add);


        jobCircularList.forEach(job -> {

            if (job.getCompanyJobTitle().getJobTitle().contains(convertCamelCase(skill))) {
                jobCircularResponses.add(modelMapper.map(job, JobCircularResponse.class));
            } else if (job.getJobResponsibility().contains(convertCamelCase(skill))) {
                jobCircularResponses.add(modelMapper.map(job, JobCircularResponse.class));
            } else if (job.getAdditionalRequirements().contains(convertCamelCase(skill))) {
                jobCircularResponses.add(modelMapper.map(job, JobCircularResponse.class));
            }
        });
        return jobCircularResponses;
    }


    public static String convertCamelCase(String skill) {
        return skill
                .toUpperCase()
                .charAt(0) + skill
                .toLowerCase()
                .substring(1);
    }
}
