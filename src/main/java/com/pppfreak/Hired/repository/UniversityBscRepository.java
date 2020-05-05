package com.pppfreak.Hired.repository;

import com.pppfreak.Hired.Entity.UniversityBsc;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityBscRepository extends CrudRepository<UniversityBsc,Integer> {
    UniversityBsc findByUniversityName(String universityName);
}
