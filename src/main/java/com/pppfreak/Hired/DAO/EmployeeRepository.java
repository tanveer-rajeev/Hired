package com.pppfreak.Hired.DAO;

import com.pppfreak.Hired.Entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Integer> {

     Employee findByUserId(String userId);
     Employee findByEmail(String email);
}
