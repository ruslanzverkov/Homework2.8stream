package com.example.homework_stream;

import com.example.homework_stream.exeption.EmployeeAlreadyAddedException;
import com.example.homework_stream.exeption.EmployeeNotFoundException;
import com.example.homework_stream.model.Employee;
import com.example.homework_stream.service.EmployeeService;
import com.example.homework_stream.service.EmployeeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import com.example.homework_stream.ConstantTest;

import static com.example.homework_stream.ConstantTest.*;


public class EmployeeServiceTest {
    private final EmployeeService out = new EmployeeServiceImpl();


    public static Stream<Arguments> params() {
        return Stream.of(
                Arguments.of(FIRST_NAME_IVAN, LAST_NAME_IVANOV, DEPARTMENT_1, SALARY_10000d)
        );
    }

    @Test
    public void addTest() {
        Employee actual = out.add(FIRST_NAME_IVAN, LAST_NAME_IVANOV, DEPARTMENT_1, SALARY_10000d);
        Employee expected = new Employee(FIRST_NAME_IVAN, LAST_NAME_IVANOV, DEPARTMENT_1, SALARY_10000d);
        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void addTestThrows() {
        Employee actual = out.add(FIRST_NAME_IVAN, LAST_NAME_IVANOV, DEPARTMENT_1, SALARY_10000d);
        Assertions.assertThrows(EmployeeAlreadyAddedException.class,
                () -> out.add(FIRST_NAME_IVAN, LAST_NAME_IVANOV, DEPARTMENT_1, SALARY_10000d));
    }

    @Test
    public void removeTest() {
        Employee expected = new Employee(FIRST_NAME_IVAN, LAST_NAME_IVANOV, DEPARTMENT_1, SALARY_10000d);
        out.add(FIRST_NAME_IVAN, LAST_NAME_IVANOV, DEPARTMENT_1, SALARY_10000d);
        Employee actual = out.remove(FIRST_NAME_IVAN, LAST_NAME_IVANOV);
        Assertions.assertEquals(actual, expected);
    }

    // не могу понять почему тест не проходит
    @Test
    public void removeTestThrows() {
        Assertions.assertThrows(EmployeeNotFoundException.class, () -> out.remove(FIRST_NAME_IVAN, LAST_NAME_IVANOV));
    }

    @Test
    public void findTest() {

        out.add(FIRST_NAME_IVAN, LAST_NAME_IVANOV, DEPARTMENT_1, SALARY_10000d);
        Employee expected = new Employee(FIRST_NAME_IVAN, LAST_NAME_IVANOV, DEPARTMENT_1, SALARY_10000d);
        Employee actual = out.find(FIRST_NAME_IVAN, LAST_NAME_IVANOV);
        Assertions.assertEquals(actual,expected);
    }

}
