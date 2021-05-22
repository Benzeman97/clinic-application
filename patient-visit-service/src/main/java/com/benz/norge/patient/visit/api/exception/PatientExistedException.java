package com.benz.norge.patient.visit.api.exception;

public class PatientExistedException extends RuntimeException {

    public PatientExistedException(String msg){
        super(msg);
    }
}
