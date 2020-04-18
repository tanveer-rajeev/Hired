package com.pppfreak.Hired.repository;

import com.pppfreak.Hired.Entity.TextileEmployee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<TextileEmployee,Integer> {

     TextileEmployee findByUserId(String userId);
     TextileEmployee findByEmail(String email);
}
