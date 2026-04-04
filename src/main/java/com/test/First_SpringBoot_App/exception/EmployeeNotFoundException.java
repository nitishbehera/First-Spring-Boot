package com.test.First_SpringBoot_App.exception;

public class EmployeeNotFoundException extends  RuntimeException{

    public EmployeeNotFoundException(String message){
        super(message);
    }
}
