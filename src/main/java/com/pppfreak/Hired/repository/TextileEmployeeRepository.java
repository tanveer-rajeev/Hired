package com.pppfreak.Hired.repository;

import com.pppfreak.Hired.Entity.TextileEmployee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextileEmployeeRepository extends CrudRepository<TextileEmployee,Integer> {
    TextileEmployee findByEmail(String email);
    TextileEmployee findByUserId(String userId);
}
