package com.kishore.issue_management.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestControllerAdvice
public class globalExceptionHandler {
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String>
    handleUserExists(UserAlreadyExistsException ex) {
        return new
                ResponseEntity<>(ex.getMessage(),HttpStatus.CONFLICT);
    }
    @RestControllerAdvice
    public class GlobalExceptionHandler {

        @ExceptionHandler(Exception.class)
        public ResponseEntity<String> handleAnyException(Exception ex) {
            ex.printStackTrace(); // IMPORTANT
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("GLOBAL HANDLER CAUGHT: " + ex.getMessage());
        }
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String>
    handleUserNotFound(UserNotFoundException ex) {
        return new
                ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
