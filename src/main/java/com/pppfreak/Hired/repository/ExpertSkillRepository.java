package com.pppfreak.Hired.repository;

import com.pppfreak.Hired.Entity.ExpertSkill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpertSkillRepository extends CrudRepository<ExpertSkill,Integer> {
    ExpertSkill findBySkill(String skill);
}
