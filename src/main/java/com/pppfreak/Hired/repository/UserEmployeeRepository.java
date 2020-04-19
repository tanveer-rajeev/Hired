package com.pppfreak.Hired.repository;

import com.pppfreak.Hired.Entity.UserEmployee;
import org.springframework.data.repository.CrudRepository;

public interface UserEmployeeRepository extends CrudRepository<UserEmployee,Integer> {
    public UserEmployee findByEmail(String email);
    public UserEmployee findByUserId(String userId);
}
