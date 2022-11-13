package com.example.homework_stream.service;

import com.example.homework_stream.model.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Employee findEmployeeWithMaxSalaryFromDepartment(int department);
    Employee findEmployeeWithMixSalaryFromDepartment(int department);
    List<Employee> findEmployeesFromDepartment(int department);
    Map<Integer,List<Employee>> findEmployees();

}
