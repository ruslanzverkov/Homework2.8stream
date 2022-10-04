package com.example.homework_stream.controller;

import com.example.homework_stream.model.Employee;
import com.example.homework_stream.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping(path = "/add")
    public Employee addEmployee(@RequestParam(value = "firstName") String firstName,
                                @RequestParam(value = "LastName") String lastName,
                                @RequestParam(value = "salary") int salary,
                                @RequestParam(value = "department") int department) {
        return service.add(firstName, lastName, salary, department);
    }

    @GetMapping(path = "/remove")
    public Employee removeEmployee(@RequestParam(value = "firstName") String firstName,
                                   @RequestParam(value = "lastNameName") String lastName) {

        return service.remove(firstName, lastName);
    }

    @GetMapping(path = "/find")
    public Employee findEmployee(@RequestParam(value = "firstName")  String firstName,
                                 @RequestParam(value = "lastName")  String lastName) {
        return service.find(firstName, lastName);
    }

    @GetMapping
    public Collection<Employee> findAll() {
        return service.findAll();
    }


    @GetMapping(path = "/departments/max-salary")
    public Employee maxSalary(@RequestParam(value = "departmentId") int department) {
        return service.getMaxSalary(department);
    }
    @GetMapping(path = "/departments/min-salary")
    public Employee minSalary(@RequestParam(value = "departmentId") int department) {
        return service.getMinSalary(department);
    }
    @GetMapping(path = "/departments/all")
    public Employee allEmployeeInDepartment(@RequestParam(value = "departmentId") int department) {
        return (Employee) service.getAllEmployeeInDepartment(department);
    }
    @GetMapping(path = "/departments/all")
    public Employee AllEmployeeMultipleDepartment() {
        return (Employee) service.getAllEmployeeMultipleDepartment();
    }

}
