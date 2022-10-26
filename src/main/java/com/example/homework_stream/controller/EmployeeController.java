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

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    public EmployeeController(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
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


    @GetMapping(path = "/departments/max-salary")
    public Employee maxSalary(@RequestParam(value = "departmentId") int department) {
        return departmentService.findEmployeeWithMaxSalaryFromDepartment(department);
    }
    @GetMapping(path = "/departments/min-salary")
    public Employee minSalary(@RequestParam(value = "departmentId") int department) {
        return departmentService.findEmployeeWithMixSalaryFromDepartment(department);
    }

    @GetMapping(path = "/departments/all")
    public String allEmployeeInDepartment(@RequestParam(value = "departmentId") int department) {

        List<Employee> employees = null;
        try {
            employees = departmentService.findEmployeesFromDepartment(department);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return employees.toString();
    }

    @GetMapping(path = "/departments")
    public String AllEmployeeMultipleDepartment() {
        List<Employee> employees = null;
        try {
            employees = service.getAllEmployeeMultipleDepartment();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return employees.toString();
    }

    @GetMapping(path = "/getAll")
    public String employee() {
        List<Employee> employees = null;
        try {
            employees = service.getEmployees();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return employees.toString();
    }

}
