package com.example.homework_stream.service;

import com.example.homework_stream.exeption.EmployeeAlreadyAddedException;
import com.example.homework_stream.exeption.EmployeeNotFoundException;
import com.example.homework_stream.exeption.InvalidNameCharactersException;
import com.example.homework_stream.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isAllBlank;
import static org.apache.commons.lang3.StringUtils.isAlpha;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<String,Employee> employees;

    public EmployeeServiceImpl() {
        this.employees = new HashMap<>();
    }



    private String getKey(String firstName, String lastName) {
        return firstName+"|"+lastName;
    }

    @Override
    public Employee add(String firstName, String lastName,int department,double salary) {
        checkAvailabilityDepartment(department);
        validateFirstAndLastName(firstName, lastName);
        Employee employee = new Employee(firstName, lastName,department,salary);
        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(employee.getFullName(),employee);
        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        validateFirstAndLastName(firstName, lastName);
        String key = getKey(firstName, lastName);
        if (employees.containsKey(key)) {
            return employees.remove(key);
        } else {
            throw new EmployeeNotFoundException("Сотрудник не найден, проверьте правильность ввода данных!");
        }

    }

    @Override
    public Employee find(String firstName, String lastName) {
        validateFirstAndLastName(firstName, lastName);
        String key = getKey(firstName, lastName);
        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException("Сотрудник не найден, проверьте правильность ввода данных!");
        }
        return employees.get(key);
    }

    @Override
    public Collection<Employee> findAll() {
        return Collections.unmodifiableCollection(employees.values());
    }

    @Override
    public void validateFirstAndLastName(String firstName, String lastName) {
        if (isAllBlank(firstName) || isAllBlank(lastName)
                || !isAlpha(firstName) || !isAlpha(lastName)) {
            throw new InvalidNameCharactersException("Неверное имя и фамилия");
        }
    }
    @Override
    public void checkAvailabilityDepartment(int departmentId) {
        if (departmentId <= 0 || departmentId > Employee.NUMBER_OF_DEPARTMENTS) {
            throw new IllegalArgumentException("Неверный номер отдела, допустимое значение от 1 до "
                    + Employee.NUMBER_OF_DEPARTMENTS);
        }
    }
}

