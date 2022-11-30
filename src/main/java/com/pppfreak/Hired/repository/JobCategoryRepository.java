package com.pppfreak.Hired.repository;

import com.pppfreak.Hired.Entity.JobCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobCategoryRepository extends CrudRepository<JobCategory, Integer> {
    JobCategory findByCategory(String category);
}
