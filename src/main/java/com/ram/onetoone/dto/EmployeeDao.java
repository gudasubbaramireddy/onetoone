package com.ram.onetoone.dto;

import com.ram.onetoone.entity.Employee;

import java.util.List;

public interface EmployeeDao {
    List<Employee> findAll();

}
