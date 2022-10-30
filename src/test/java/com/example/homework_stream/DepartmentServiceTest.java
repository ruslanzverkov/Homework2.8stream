package com.example.homework_stream;

import com.example.homework_stream.exeption.EmployeeNotFoundException;
import com.example.homework_stream.model.Employee;
import com.example.homework_stream.service.DepartmentService;
import com.example.homework_stream.service.DepartmentServiceImpl;
import com.example.homework_stream.service.EmployeeService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static com.example.homework_stream.ConstantTest.*;
import static com.example.homework_stream.ConstantTest.SALARY_10000d;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {


    private DepartmentService departmentService;
    @Mock
    private EmployeeService employeeService;

    @BeforeEach
    public void beforeEach() {
        departmentService = new DepartmentServiceImpl(employeeService);
        List<Employee> employees = List.of(
                new Employee("FIRST_NAME_IVAN", "LAST_NAME_IVANOV", 1, 10000),
                new Employee("FIRST_NAME_PETR", "LAST_NAME_PETROV", 2, 20000),
                new Employee("FIRST_NAME_DENIS", "LAST_NAME_DENISOV", 5, 30000)
        );
        when(employeeService.findAll()).thenReturn(employees);
    }

    @ParameterizedTest
    @MethodSource("employeeWithMaxSalary")
    public void employeeWithMaxSalaryTest(int department, Employee expected) {
        assertThat(departmentService.findEmployeeWithMaxSalaryFromDepartment(department)).isEqualTo(expected);
    }


    @Test
    public void employeeWithMaxSalaryThrowsTest() {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> departmentService.findEmployeeWithMaxSalaryFromDepartment(3));

    }

    @ParameterizedTest
    @MethodSource("employeeWithMinSalary")
    public void employeeWithMiSalaryTest(int department, Employee expected) {
        assertThat(departmentService.findEmployeeWithMixSalaryFromDepartment(department)).isEqualTo(expected);

    }

    @Test
    public void employeeWithMinSalaryThrowsTest() {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> departmentService.findEmployeeWithMixSalaryFromDepartment(3));

    }

    @ParameterizedTest
    @MethodSource("employeeFindFromDepartment")
    public void employeeFindTest(int department, List<Employee> expected) {
        assertThat(departmentService.findEmployeesFromDepartment(department)).containsExactlyInAnyOrderElementsOf(expected);
    }



    public static Stream<Arguments> employeeWithMaxSalary() {

        return Stream.of(
                Arguments.of(1, new Employee("FIRST_NAME_IVAN", "LAST_NAME_IVANOV", 1, 10000)),
                Arguments.of(5, new Employee("FIRST_NAME_DENIS", "LAST_NAME_DENISOV", 5, 30000))
        );
    }

    public static Stream<Arguments> employeeWithMinSalary() {
        return Stream.of(
                Arguments.of(1, new Employee("FIRST_NAME_IVAN", "LAST_NAME_IVANOV", 1, 10000)),
                Arguments.of(2, new Employee("FIRST_NAME_PETR", "LAST_NAME_PETROV", 2, 20000))
        );
    }

    public static Stream<Arguments> employeeFindFromDepartment() {
        return Stream.of(
                Arguments.of(5, List.of(
                        new Employee("FIRST_NAME_IVAN", "LAST_NAME_IVANOV", 1, 10000),
                        new Employee("FIRST_NAME_PETR", "LAST_NAME_PETROV", 2, 20000),
                        new Employee("FIRST_NAME_DENIS", "LAST_NAME_DENISOV", 5, 30000))
                ),
                Arguments.of(5, List.of(
                        new Employee("FIRST_NAME_PETR", "LAST_NAME_PETROV", 2, 20000),
                        new Employee("FIRST_NAME_DENIS", "LAST_NAME_DENISOV", 5, 30000))
                ),
                Arguments.of(3, Collections.emptyList())
        );
    }


}

