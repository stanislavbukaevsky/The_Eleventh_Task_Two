package pro.sky.employeebook.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.employeebook.entity.Employee;
import pro.sky.employeebook.exception.EmployeeAlreadyAddedException;
import pro.sky.employeebook.exception.EmployeeNotFoundException;
import pro.sky.employeebook.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/add")
    public String addEmployee(@RequestParam("firstName") String firstName,
                              @RequestParam("lastName") String lastName,
                              @RequestParam("department") Integer department,
                              @RequestParam("salary") Integer salary) throws EmployeeAlreadyAddedException {
        Employee listOfEmployee = null;

        try {
            listOfEmployee = employeeService.addEmployee(firstName, lastName, department, salary);
        } catch (Throwable e) {
            return e.getMessage();
        }
        return listOfEmployee.toString();
    }

    @GetMapping(path = "/delete")
    public String deleteEmployee(@RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName) throws EmployeeNotFoundException {
        Employee listOfEmployee = null;

        try {
            listOfEmployee = employeeService.deleteEmployee(firstName, lastName);
        } catch (Throwable e) {
            return e.getMessage();
        }
        return listOfEmployee.toString();
    }

    @GetMapping(path = "/find")
    public String toFindEmployee(@RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName) throws EmployeeNotFoundException {
        Employee listOfEmployee = null;

        try {
            listOfEmployee = employeeService.toFindEmployee(firstName, lastName);
        } catch (Throwable e) {
            return e.getMessage();
        }
        return listOfEmployee.toString();
    }

    @GetMapping(path = "/with")
    public Object printEmployee() {
        List<Employee> listOfEmployees = null;

        try {
            listOfEmployees = employeeService.printEmployees();
        } catch (Throwable e) {
            return e.getMessage();
        }
        return listOfEmployees;
    }
}
