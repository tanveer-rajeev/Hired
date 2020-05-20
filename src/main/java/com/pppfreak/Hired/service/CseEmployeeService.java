package com.pppfreak.Hired.service;

import com.pppfreak.Hired.Entity.CseEmployee;
import com.pppfreak.Hired.form.request.CseEmployeeRequestForm;
import com.pppfreak.Hired.response.CseEmployeeResponse;

import java.util.List;
import java.util.Optional;

public interface CseEmployeeService {


    List<CseEmployee> getAllCseEmployee();

    CseEmployeeResponse addCseEmployee(CseEmployeeRequestForm employee);

    CseEmployeeResponse updateCseEmployee(CseEmployeeRequestForm employee,Integer id);

    String deleteCseEmployee(Integer id);

    Optional<CseEmployee> getCseEmployeeById(Integer id);


}
