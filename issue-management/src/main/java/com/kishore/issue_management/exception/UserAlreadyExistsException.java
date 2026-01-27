package com.kishore.issue_management.exception;

public class UserAlreadyExistsException extends RuntimeException{
    public
    UserAlreadyExistsException(String message){
        super(message);
    }
}
