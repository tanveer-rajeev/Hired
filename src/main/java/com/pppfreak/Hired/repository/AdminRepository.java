package com.pppfreak.Hired.repository;

import com.pppfreak.Hired.Entity.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository  extends CrudRepository<Admin,Integer> {
    Admin findByUserId(String userId);
    Admin findByEmail(String email);
}
