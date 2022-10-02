package com.example.homework_stream.service;

import com.example.homework_stream.exeption.EmployeeAlreadyAddedException;
import com.example.homework_stream.exeption.EmployeeNotFoundException;
import com.example.homework_stream.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final List< Employee> employees;

    public EmployeeServiceImpl(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public Employee add(String firstName, String lastName,int salary, int department) {
        Employee employee = new Employee(firstName, lastName, salary, department);
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.add(employee);
        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        Employee employee = find(firstName, lastName);
        employees.remove(employee);

        throw new EmployeeNotFoundException();
    }

    @Override
    public Employee find(String firstName, String lastName) {
        Optional<Employee> employee=employees.stream()
                .filter(e->e.getFirstName().equals(firstName)&&e.getLastName().equals(lastName))
                .findAny();

        return employee.orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public Collection<Employee> findAll() {
        return Collections.unmodifiableCollection(employees);
    }
}