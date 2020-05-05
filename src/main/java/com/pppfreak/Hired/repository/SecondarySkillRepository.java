package com.pppfreak.Hired.repository;

import com.pppfreak.Hired.Entity.SecondarySkill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecondarySkillRepository extends CrudRepository<SecondarySkill,Integer> {
    SecondarySkill findBySkill(String skill);
}
