package com.hospital.exception;

public class PatientDuplicateException  extends RuntimeException{
    public PatientDuplicateException(String message) {
        super(message);
    }
}
