package com.kishore.issue_management.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.server.ui.OneTimeTokenSubmitPageGeneratingWebFilter;
import org.springframework.web.bind.annotation.*;
@RestControllerAdvice
public class globalExceptionHandler {
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String>
    handleUserExists(UserAlreadyExistsException ex) {
        return new
                ResponseEntity<>(ex.getMessage(),HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String>
    handleUserNotFound(UserNotFoundException ex) {
        return new
                ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
