package com.example.homework_stream.service;

import com.example.homework_stream.exeption.InvalidNameCharactersException;
import com.example.homework_stream.model.Employee;

import java.util.Collection;
import java.util.List;

public interface EmployeeService {

    Employee add(String firstName, String lastName,int department,double salary);

    Employee remove(String firstName, String lastName);

    Employee find(String firstName, String lastName);

    Collection<Employee> findAll();
}