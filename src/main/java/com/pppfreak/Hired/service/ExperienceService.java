package com.pppfreak.Hired.service;

import com.pppfreak.Hired.Entity.Experience;

import java.util.List;

public interface ExperienceService {

    Experience getExperience(Integer id);
    List<Experience> getAllExperience();
    void addExperience(Experience experience);
    void addAllExperience(List<Experience> experienceList);
    void updateExperience(Experience experience);
    void deleteExperience(Integer id);
}
