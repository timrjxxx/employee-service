package com.employeeservice.handler;

import com.employeeservice.dto.APIResponse;
import com.employeeservice.dto.ErrorDTO;
import com.employeeservice.exception.EmployeeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<APIResponse<?>> handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
        ErrorDTO errorDTO = new ErrorDTO("employeeId", ex.getMessage());

        APIResponse<?> response = APIResponse
                .<Object>builder()
                .status("ERROR")
                .errors(Collections.singletonList(errorDTO))
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
