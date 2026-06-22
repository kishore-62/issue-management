package com.kishore.issue_management.controller;

import com.kishore.issue_management.dto.LoginRequest;
import com.kishore.issue_management.dto.LoginResponse;
import com.kishore.issue_management.dto.RegisterRequest;
import com.kishore.issue_management.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // ✅ Register User
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {

        userService.register(
                request.getName(),
                request.getEmail(),
                request.getPassword(),
                request.getRole()
        );

        return ResponseEntity.ok("User registered successfully");
    }

    // ✅ Login User
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {

        String token = userService.login(
                request.getEmail(),
                request.getPassword()
        );

        return ResponseEntity.ok(new LoginResponse(token));
    }
}