package com.example.homework_stream.controller;

import com.example.homework_stream.model.Employee;
import com.example.homework_stream.service.DepartmentService;
import com.example.homework_stream.service.DepartmentServiceImpl;
import com.example.homework_stream.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;

    }

    @GetMapping(path = "/add")
    public Employee addEmployee(@RequestParam(value = "firstName") String firstName,
                                @RequestParam(value = "LastName") String lastName,
                                @RequestParam(value = "salary") int department,
                                @RequestParam(value = "department") double salary) {
        return employeeService.add(firstName, lastName,department, salary );
    }

    @GetMapping(path = "/remove")
    public Employee removeEmployee(@RequestParam(value = "firstName") String firstName,
                                   @RequestParam(value = "lastNameName") String lastName) {

        return employeeService.remove(firstName, lastName);
    }

    @GetMapping(path = "/find")
    public Employee findEmployee(@RequestParam(value = "firstName")  String firstName,
                                 @RequestParam(value = "lastName")  String lastName) {
        return employeeService.find(firstName, lastName);
    }

    @GetMapping
    public Collection<Employee> findAll() {
        return employeeService.findAll();
    }




}
