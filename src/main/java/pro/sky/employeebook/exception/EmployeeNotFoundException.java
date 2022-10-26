package pro.sky.employeebook.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmployeeNotFoundException extends Exception {
    public EmployeeNotFoundException(Object err_empl_not_found) {
    }
}
