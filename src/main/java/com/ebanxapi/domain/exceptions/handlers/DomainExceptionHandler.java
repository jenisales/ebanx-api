package com.ebanxapi.domain.exceptions.handlers;

import com.ebanxapi.domain.exceptions.NonExistingAccount;
import com.ebanxapi.domain.exceptions.UserBalanceNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class DomainExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserBalanceNotFound.class)
    public ResponseEntity<Double> userBalanceNotFoundHandler(){
        return new ResponseEntity<Double>(0.0, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NonExistingAccount.class)
    public ResponseEntity<Double> nonExistingAccountHandler(){
        return new ResponseEntity<Double>(0.0, HttpStatus.NOT_FOUND);
    }

}
