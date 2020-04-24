package com.pppfreak.Hired.service;

import com.pppfreak.Hired.Entity.CseEmployee;
import com.pppfreak.Hired.form.request.CseEmployeeRequestForm;
import com.pppfreak.Hired.response.CseEmployeeResponse;

import java.util.List;

public interface CseEmployeeService {


    CseEmployee getEmployeeByUserId(String userId);

    List<CseEmployee> getAllEmployee();

    CseEmployeeResponse addCseEmployee(CseEmployeeRequestForm employee,Integer employeeId);


}
