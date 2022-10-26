package pro.sky.employeebook.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.employeebook.entity.Employee;
import pro.sky.employeebook.service.DepartmentService;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/max-salary")
    public String maxSalary(@RequestParam("department-max") Integer department) {
        Employee listOfEmployee = null;

        try {
            listOfEmployee = departmentService.maxSalary(department);
        } catch (Throwable e) {
            return e.getMessage();
        }
        return listOfEmployee.toString();
    }

    @GetMapping(path = "/min-salary")
    public String minSalary(@RequestParam("department-min") Integer department) {
        Employee listOfEmployee = null;

        try {
            listOfEmployee = departmentService.minSalary(department);
        } catch (Throwable e) {
            return e.getMessage();
        }
        return listOfEmployee.toString();
    }

    @GetMapping(path = "/department-all")
    public String employeesOfTheDepartment(@RequestParam("all") Integer department) {
        List<Employee> listOfEmployee = null;

        try {
            listOfEmployee = departmentService.employeesOfTheDepartment(department);
        } catch (Throwable e) {
            return e.getMessage();
        }
        return listOfEmployee.toString();
    }

    @GetMapping(path = "/department/all")
    public String employeesWithDivisionOfDepartments() {
        List<Employee> listOfEmployee = null;

        try {
            listOfEmployee = departmentService.employeesWithDivisionOfDepartments();
        } catch (Throwable e) {
            return e.getMessage();
        }
        return listOfEmployee.toString();
    }
}
