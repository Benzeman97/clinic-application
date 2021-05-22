package com.benz.norge.patient.visit.api.exception;

import com.benz.norge.patient.visit.api.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PatientExistedExceptionHandler {

    @ExceptionHandler(PatientExistedException.class)
    public ResponseEntity<ErrorMessage> toResponse(PatientExistedException ex){
        ErrorMessage errorMessage=new ErrorMessage(HttpStatus.CONFLICT.value(),ex.getMessage(),"www.benz.com");
        return new ResponseEntity<>(errorMessage,HttpStatus.CONFLICT);
    }
}
