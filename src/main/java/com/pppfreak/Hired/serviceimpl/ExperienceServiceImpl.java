package com.pppfreak.Hired.serviceimpl;

import com.pppfreak.Hired.repository.ExperienceRepository;
import com.pppfreak.Hired.Entity.Experience;
import com.pppfreak.Hired.service.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ExperienceServiceImpl  implements ExperienceService{
    @Autowired
    ExperienceRepository experienceRepository;



    @Override
    public Experience getExperience(Integer id) {
        Optional<Experience> experience = experienceRepository.findById(id);
        Experience theExperience = null;
        if (experience.isPresent()) {
            theExperience = experience.get();
        } else {
            throw new RuntimeException("Experience is not found " + id);
        }
        return theExperience;
    }

    @Override
    public List<Experience> getAllExperience() {
        List<Experience> experiences = new ArrayList<>();
        experienceRepository.findAll().forEach(experience -> experiences.add(experience));
        return experiences;

    }

    @Override
    public void addExperience(Experience experience) {
        experienceRepository.save(experience);
    }

    @Override
    public void addAllExperience(List<Experience> experienceList) {
        experienceRepository.saveAll(experienceList);
    }


    @Override
    public void updateExperience(Experience experience) {
        experienceRepository.save(experience);
    }

    @Override
    public void deleteExperience(Integer id) {
        experienceRepository.deleteById(id);
    }
}
