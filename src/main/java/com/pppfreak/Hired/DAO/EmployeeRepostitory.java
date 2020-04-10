package com.pppfreak.Hired.DAO;

import com.pppfreak.Hired.Entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepostitory  extends CrudRepository<Employee,Integer> {

}
