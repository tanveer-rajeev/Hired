package com.pppfreak.Hired.DAO;

import com.pppfreak.Hired.Entity.Experience;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienceRepository extends CrudRepository<Experience,Integer > {
}
