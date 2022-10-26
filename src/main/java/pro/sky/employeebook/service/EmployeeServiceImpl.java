package pro.sky.employeebook.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.employeebook.entity.Employee;
import pro.sky.employeebook.exception.EmployeeAlreadyAddedException;
import pro.sky.employeebook.exception.EmployeeNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final String ERR_EMPL_NOT_FOUND = "Сотрудник с таким именем не существует";
    private final String ERR_INVALID_NAME = "Неверно введено имя";
    private final List<Employee> employeesList = new ArrayList<>();

    @Override
    public Employee addEmployee(String firstName, String lastName, Integer department, Integer salary) throws EmployeeAlreadyAddedException, EmployeeNotFoundException {
        validate(firstName, lastName);
        Employee listOfEmployee = new Employee(capsLetter(firstName), capsLetter(lastName), department, salary);

        if (employeesList.contains(listOfEmployee)) {
            throw new EmployeeAlreadyAddedException();
        }
        employeesList.add(listOfEmployee);
        return listOfEmployee;
    }

    @Override
    public Employee deleteEmployee(String firstName, String lastName) throws EmployeeNotFoundException {
        validate(firstName, lastName);
        Employee listOfEmployee = toFindEmployee(firstName, lastName);
        employeesList.remove(listOfEmployee);
        return listOfEmployee;
    }

    @Override
    public Employee toFindEmployee(String firstName, String lastName) throws EmployeeNotFoundException {
        validate(firstName, lastName);
        final Optional<Employee> listOfEmployee = employeesList.stream()
                .filter(employee -> employee.getFirstName().equals(firstName) && employee.getLastName().equals(lastName))
                .findAny();
        return listOfEmployee.orElseThrow(() -> new EmployeeNotFoundException(ERR_EMPL_NOT_FOUND));
    }

    @Override
    public List<Employee> printEmployees() {
        return new ArrayList<>(employeesList);
    }

    private String capsLetter(String string) {
        return StringUtils.upperCase(string.substring(0, 1) + string.substring(1));
    }

    private void validate(String firstName, String lastName) throws EmployeeNotFoundException {
        if (!(StringUtils.isAlpha(firstName)) && (StringUtils.isAlpha(lastName))) {
            throw new EmployeeNotFoundException(ERR_INVALID_NAME);
        }
    }
}
