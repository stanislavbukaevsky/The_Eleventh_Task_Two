package pro.sky.employeebook.service;

import pro.sky.employeebook.entity.Employee;
import pro.sky.employeebook.exception.EmployeeAlreadyAddedException;
import pro.sky.employeebook.exception.EmployeeNotFoundException;

import java.util.List;

public interface EmployeeService {
    Employee addEmployee(String firstName, String lastName, Integer department, Integer salary) throws EmployeeAlreadyAddedException, EmployeeNotFoundException;

    Employee deleteEmployee(String firstName, String lastName) throws EmployeeNotFoundException;

    Employee toFindEmployee(String firstName, String lastName) throws EmployeeNotFoundException;

    List<Employee> printEmployees();
}
