package com.pppfreak.Hired.repository;

import com.pppfreak.Hired.Entity.JobCircular;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobCircularRepository extends CrudRepository<JobCircular,Integer> {
    JobCircular findByJobTitle(String title);
}
