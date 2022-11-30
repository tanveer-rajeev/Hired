package com.pppfreak.Hired.repository;

import com.pppfreak.Hired.Entity.JobCircular;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobCircularRepository extends CrudRepository<JobCircular, Integer> {
    List<JobCircular> findByJobLocation(String location);
}
