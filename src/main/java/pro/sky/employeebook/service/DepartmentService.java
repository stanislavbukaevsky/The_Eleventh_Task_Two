package pro.sky.employeebook.service;

import pro.sky.employeebook.entity.Employee;
import pro.sky.employeebook.exception.EmployeeNotFoundException;

import java.util.List;

public interface DepartmentService {

    Employee maxSalary(Integer department) throws EmployeeNotFoundException;

    Employee minSalary(Integer department) throws EmployeeNotFoundException;

    List<Employee> employeesOfTheDepartment(Integer department);

    List<Employee> employeesWithDivisionOfDepartments();
}
