package com.example.homework_stream.service;

import com.example.homework_stream.model.Employee;

import java.util.Collection;
import java.util.List;

public interface EmployeeService {
    Employee add(String firstName, String lastName, int salary, int department);

    Employee remove(String firstName, String lastName);

    Employee find(String firstName, String lastName);

    Employee getMaxSalary(int department);

    Employee getMinSalary(int department);

    List<Employee> getAllEmployeeInDepartment(int department);

    List<Employee> getAllEmployeeMultipleDepartment();

    Collection<Employee> findAll();
    List<Employee> getEmployees();
}