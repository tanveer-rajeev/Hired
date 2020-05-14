package com.pppfreak.Hired.repository;

import com.pppfreak.Hired.Entity.JobApplyForm;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeJobApplyRepository extends CrudRepository<JobApplyForm,Integer> {
}
