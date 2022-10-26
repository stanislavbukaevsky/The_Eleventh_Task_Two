package pro.sky.employeebook.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.employeebook.entity.Employee;
import pro.sky.employeebook.exception.EmployeeNotFoundException;
import pro.sky.employeebook.service.DepartmentServiceImpl;
import pro.sky.employeebook.service.EmployeeServiceImpl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceMockTests {

    @Mock
    private EmployeeServiceImpl employeeService;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    public static Stream<Arguments> parameters() {
        return Stream.of(
                Arguments.of(1, new Employee("Sokolov", "Artem", 1, 20500)),
                Arguments.of(2, new Employee("Pankratova", "Angelina", 2, 22500))
        );
    }

    public static Stream<Arguments> employeesOfTheDepartmentParameters() {
        return Stream.of(
                Arguments.of(1, List.of(
                        new Employee("Sokolov", "Artem", 1, 20500),
                        new Employee("Maslova", "Sofiy", 1, 34500))),
                Arguments.of(2, List.of(
                        new Employee("Pankratova", "Angelina", 2, 22500),
                        new Employee("Ivanova", "Yroslava", 2, 30500),
                        new Employee("Zinoviev", "Nikita", 2, 32500))),
                Arguments.of(3, Collections.emptyList())
        );
    }

    @BeforeEach
    public void beforeEach() {
        List<Employee> employees = List.of(
                new Employee("Sokolov", "Artem", 1, 20500),
                new Employee("Pankratova", "Angelina", 2, 22500),
                new Employee("Ivanova", "Yroslava", 2, 30500),
                new Employee("Zinoviev", "Nikita", 2, 32500),
                new Employee("Maslova", "Sofiy", 1, 34500)
        );
        when(employeeService.printEmployees()).thenReturn(employees);
    }

    @ParameterizedTest
    @MethodSource("parameters")
    public void maxSalaryTestOne(int department, Employee expected) throws EmployeeNotFoundException {
        assertThat(departmentService.maxSalary(department)).isEqualTo(expected);
    }

    @Test
    public void maxSalaryTestTwo() {
        assertThatExceptionOfType(Exception.class)
                .isThrownBy(() -> departmentService.maxSalary(1));
    }

    @ParameterizedTest
    @MethodSource("parameters")
    public void minSalaryTestOne(Integer department, Employee expected) throws EmployeeNotFoundException {
        assertThat(departmentService.minSalary(department)).isEqualTo(expected);
    }

    @Test
    public void minSalaryTestTwo() {
        assertThatExceptionOfType(Exception.class)
                .isThrownBy(() -> departmentService.minSalary(2));
    }

    @ParameterizedTest
    @MethodSource("employeesOfTheDepartmentParameters")
    public void employeesOfTheDepartmentTestOne(Integer department, List<Employee> expected) {
        assertThat(departmentService.employeesOfTheDepartment(department)).containsExactlyElementsOf(expected);
    }

    @Test
    public void employeesOfTheDepartmentTestTwo() {
        assertThat(departmentService.employeesWithDivisionOfDepartments()).containsExactlyElementsOf(
                List.of(
                        new Employee("Sokolov", "Artem", 1, 20500),
                        new Employee("Pankratova", "Angelina", 2, 22500),
                        new Employee("Ivanova", "Yroslava", 2, 30500),
                        new Employee("Zinoviev", "Nikita", 2, 32500),
                        new Employee("Maslova", "Sofiy", 1, 34500)
                )
        );
    }
}
