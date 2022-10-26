package pro.sky.employeebook.entity;

import java.util.Objects;

import static org.apache.tomcat.util.IntrospectionUtils.capitalize;

public class Employee {

    private String lastName;
    private String firstName;
    private Integer department;
    private Integer salary;


    public Employee(String lastName, String firstName, Integer department, Integer salary) {
        this.lastName = lastName;
        this.lastName = capitalize(lastName.toLowerCase());
        this.firstName = firstName;
        this.firstName = capitalize(firstName.toLowerCase());
        this.department = department;
        this.salary = salary;
    }


    public String getLastName() {
        return this.lastName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public int getDepartment() {
        return this.department;
    }

    public int getSalary() {
        return this.salary;
    }

    @Override
    public String toString() {
        return "Данные о сотруднике: " + lastName + " " + firstName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return lastName.equals(employee.lastName) && firstName.equals(employee.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName);
    }
}
