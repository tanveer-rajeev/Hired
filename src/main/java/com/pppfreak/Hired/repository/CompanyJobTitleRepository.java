package com.pppfreak.Hired.repository;

import com.pppfreak.Hired.Entity.CompanyJobTitle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyJobTitleRepository extends CrudRepository<CompanyJobTitle,Integer> {
    CompanyJobTitle findByJobTitle(String jobTitle);
}
