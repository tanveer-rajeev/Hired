package com.pppfreak.Hired.repository;

import com.pppfreak.Hired.Entity.JobField;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobFieldRepository extends CrudRepository<JobField, Integer> {
    JobField findByField(String role);
}
