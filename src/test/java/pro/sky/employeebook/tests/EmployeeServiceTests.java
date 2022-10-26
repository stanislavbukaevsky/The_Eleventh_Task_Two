package pro.sky.employeebook.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pro.sky.employeebook.entity.Employee;
import pro.sky.employeebook.exception.EmployeeAlreadyAddedException;
import pro.sky.employeebook.exception.EmployeeNotFoundException;
import pro.sky.employeebook.service.EmployeeService;
import pro.sky.employeebook.service.EmployeeServiceImpl;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class EmployeeServiceTests {

    private final EmployeeService employeeService = new EmployeeServiceImpl();

    public static Stream<Arguments> parameters() {
        return Stream.of(
                Arguments.of("Sokolov", "Artem", 1, 20500),
                Arguments.of("Pankratova", "Angelina", 2, 22500),
                Arguments.of("Orlov", "Dmitry", 3, 24500),
                Arguments.of("Kuznecova", "Sofiy", 4, 26500),
                Arguments.of("Soloviev", "Vycheslav", 5, 28500),
                Arguments.of("Ivanova", "Yroslava", 2, 30500),
                Arguments.of("Zinoviev", "Nikita", 4, 32500),
                Arguments.of("Maslova", "Sofiy", 1, 34500),
                Arguments.of("Trofimov", "Roman", 3, 36500),
                Arguments.of("Ykovleva", "Mary", 5, 38500)
        );
    }

    @ParameterizedTest
    @MethodSource("parameters")
    public void addEmployeeTestOne(String firstName, String lastName, Integer department, Integer salary) throws EmployeeAlreadyAddedException, EmployeeNotFoundException {
        Employee expected = new Employee(firstName, lastName, department, salary);

        assertThat(employeeService.printEmployees().isEmpty());
        employeeService.addEmployee(firstName, lastName, department, salary);
        assertThat(employeeService.printEmployees())
                .hasSize(1)
                .containsExactly(expected);
        assertThat(employeeService.toFindEmployee(expected.getFirstName(), expected.getLastName()))
                .isNotNull()
                .isEqualTo(expected);

        assertThatExceptionOfType(Exception.class)
                .isThrownBy(() -> employeeService.addEmployee(firstName, lastName, department, salary));
    }

    @Test
    public void addEmployeeTestTwo() {
        assertThatExceptionOfType(Exception.class)
                .isThrownBy(() -> employeeService.addEmployee("Sokolov123", "Artem", 1, 20500));
        assertThatExceptionOfType(Exception.class)
                .isThrownBy(() -> employeeService.addEmployee("Pankratova!@#", "Angelina", 2, 22500));
        assertThatExceptionOfType(Exception.class)
                .isThrownBy(() -> employeeService.addEmployee("Orlov", null, 3, 24500));
    }

    @ParameterizedTest
    @MethodSource("parameters")
    public void deleteEmployeeTestOne(String firstName, String lastName, Integer department, Integer salary) throws EmployeeAlreadyAddedException, EmployeeNotFoundException {
        assertThat(employeeService.printEmployees().isEmpty());
        assertThatExceptionOfType(Exception.class)
                .isThrownBy(() -> employeeService.deleteEmployee("Bukaevsky", "Stanislav"));

        Employee expected = new Employee(firstName, lastName, department, salary);
        employeeService.addEmployee(firstName, lastName, department, salary);
        assertThat(employeeService.printEmployees())
                .hasSize(1)
                .containsExactly(expected);

        assertThatExceptionOfType(Exception.class)
                .isThrownBy(() -> employeeService.deleteEmployee("Bukaevsky", "Stanislav"));
    }

    @ParameterizedTest
    @MethodSource("parameters")
    public void deleteEmployeeTestTwo(String firstName, String lastName, Integer department, Integer salary) throws EmployeeNotFoundException, EmployeeAlreadyAddedException {
        assertThat(employeeService.printEmployees()).isEmpty();
        Employee expected = new Employee(firstName, lastName, department, salary);
        employeeService.addEmployee(firstName, lastName, department, salary);

        assertThat(employeeService.printEmployees())
                .hasSize(1)
                .containsExactly(expected);

        assertThat(employeeService.deleteEmployee(lastName, firstName)).isEqualTo(expected);
        assertThat(employeeService.printEmployees()).isEmpty();
    }

    @ParameterizedTest
    @MethodSource("parameters")
    public void toFindEmployeeTestOne(String firstName, String lastName, Integer department, Integer salary) throws EmployeeAlreadyAddedException, EmployeeNotFoundException {
        assertThat(employeeService.printEmployees()).isEmpty();
        assertThatExceptionOfType(Exception.class)
                .isThrownBy(() -> employeeService.toFindEmployee("Bukaevsky", "Stanislav"));

        Employee expected = new Employee(firstName, lastName, department, salary);
        employeeService.addEmployee(firstName, lastName, department, salary);
        assertThat(employeeService.printEmployees())
                .hasSize(1)
                .containsExactly(expected);

        assertThatExceptionOfType(Exception.class)
                .isThrownBy(() -> employeeService.toFindEmployee("Bukaevsky", "Stanislav"));
    }

    @ParameterizedTest
    @MethodSource("parameters")
    public void toFindEmployeeTestTwo(String firstName, String lastName, Integer department, Integer salary) throws EmployeeAlreadyAddedException, EmployeeNotFoundException {
        assertThat(employeeService.printEmployees()).isEmpty();
        Employee expected = new Employee(firstName, lastName, department, salary);
        employeeService.addEmployee(firstName, lastName, department, salary);

        assertThat(employeeService.printEmployees())
                .hasSize(1)
                .containsExactly(expected);

        assertThat(employeeService.toFindEmployee(lastName, firstName)).isEqualTo(expected);
    }
}
