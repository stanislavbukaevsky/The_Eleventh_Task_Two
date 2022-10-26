package pro.sky.employeebook.service;

import org.springframework.stereotype.Service;
import pro.sky.employeebook.entity.Employee;
import pro.sky.employeebook.exception.EmployeeNotFoundException;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final String ERR_EMPL_NOT_FOUND = "Сотрудник с таким именем не существует";
    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee maxSalary(Integer department) throws EmployeeNotFoundException {
        return employeeService.printEmployees().stream()
                .filter(employee -> employee.getDepartment() == department)
                .max(Comparator.comparingInt(employee -> employee.getSalary()))
                .orElseThrow(() -> new EmployeeNotFoundException(ERR_EMPL_NOT_FOUND));
    }

    @Override
    public Employee minSalary(Integer department) throws EmployeeNotFoundException {
        return employeeService.printEmployees().stream()
                .filter(employee -> employee.getDepartment() == department)
                .min(Comparator.comparingInt(employee -> employee.getSalary()))
                .orElseThrow(() -> new EmployeeNotFoundException(ERR_EMPL_NOT_FOUND));
    }

    @Override
    public List<Employee> employeesOfTheDepartment(Integer department) {
        return employeeService.printEmployees().stream()
                .filter(employee -> employee.getDepartment() == department)
                .collect(Collectors.toList());
    }

    @Override
    public List<Employee> employeesWithDivisionOfDepartments() {
        return Collections.unmodifiableList(employeeService.printEmployees().stream()
                .sorted(Comparator.comparingInt(employee -> employee.getDepartment()))
                .collect(Collectors.toList()));
    }
}
