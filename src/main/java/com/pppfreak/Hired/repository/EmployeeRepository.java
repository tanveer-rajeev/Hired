package com.pppfreak.Hired.repository;

import com.pppfreak.Hired.Entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Integer> {
     Employee findByEmail(String email);
     Employee findByUserId(String userId);
}
