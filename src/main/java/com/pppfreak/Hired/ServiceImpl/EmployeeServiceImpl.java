package com.pppfreak.Hired.ServiceImpl;

import com.pppfreak.Hired.Entity.Employee;
import com.pppfreak.Hired.Entity.Experience;
import com.pppfreak.Hired.DAO.EmployeeRepostitory;
import com.pppfreak.Hired.service.EmployeeService;
import com.pppfreak.Hired.service.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepostitory employeeRepostitory;

    @Autowired
    private ExperienceService experienceService;

    @Override
    public Employee getEmployeeById(Integer id) {
        Optional<Employee> employee= employeeRepostitory.findById(id);
        Employee theEmployee =null;
        if(employee.isPresent()){
            theEmployee=employee.get();

        }else{
            throw new RuntimeException("Emplopee Not Found "+id);
        }
        return theEmployee;

    }

    @Override
    public List<Employee> getAllEmployee() {
        List<Employee> employee = new ArrayList<>();
                employeeRepostitory.findAll().forEach(employee1 -> employee.add(employee1));
                return employee;
    }


    @Override
    public void addEmployee(Employee employee)  {

        for (int i = 0; i < employee.getExperienceList().size(); i++) {
            Experience experience = employee.getExperienceList().get(i);
            experienceService.addExperience(experience);
        }

        employeeRepostitory.save(employee);
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeRepostitory.save(employee);
    }

    @Override
    public void deleteEmployee(Integer id) {
        Optional<Employee> employee = employeeRepostitory.findById(id);
        if(!employee.isPresent()){
            throw  new RuntimeException("Employee not found "+id);
        }
        employeeRepostitory.deleteById(id);
    }

    @Override
    public void deleteEmployeeExperience(Integer employeeId,Integer experienceId) {

        Optional<Employee> employee = employeeRepostitory.findById(employeeId);

        Experience experience = experienceService.getExperience(experienceId);

        if(!employee.isPresent()){
            throw  new RuntimeException("Employee not found "+employeeId);
        }
        if(experience.equals("null")){
            throw  new RuntimeException("Experience not present in employee profile "+experienceId);
        }

        experienceService.deleteExperience(experienceId);
    }



}
