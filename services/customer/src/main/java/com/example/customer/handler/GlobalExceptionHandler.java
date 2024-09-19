package com.example.customer.handler;

import com.example.customer.exception.CustomerNotFoundException;
import org.springframework.data.mongodb.core.mapping.FieldName;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    //Method to handle CustomerNotFoundException
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<String> handle(CustomerNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMsg());
    }

    //Method to handle MethodArgumentNotValidException which is generated from @Valid check
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgNotValid(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<String, String>();
        exception.getBindingResult().getAllErrors().forEach(error -> {
            // get the field name fname,lname etc
            String fieldName = ((FieldError) error).getField();
            // get default error message specified in the record class
            String msg = error.getDefaultMessage();
            errors.put(fieldName, msg);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(errors));
    }
}
