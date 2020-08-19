package com.ram.onetoone.web;

import com.ram.onetoone.dto.EmployeeDao;
import com.ram.onetoone.entity.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private EmployeeDao employeeDao;

    public EmployeeRestController(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    //expose "/employees" and return list of employees

    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeDao.findAll();
    }
}
