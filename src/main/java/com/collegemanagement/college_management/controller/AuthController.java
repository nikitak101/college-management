package com.collegemanagement.college_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.collegemanagement.college_management.service.UserService;
import com.collegemanagement.college_management.model.User;
import com.collegemanagement.college_management.dto.LoginRequest;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        System.out.println("Login Attempt: RollNo = " + request.getRollNo() + ", Role = " + request.getRole());

        if (request.getRollNo() == null || request.getPassword() == null || request.getRole() == null) {
            return ResponseEntity.status(400).body("Missing required fields");
        }

        User user = userService.getUserByCredentials(request.getRollNo(), request.getPassword(), request.getRole());

        if (user == null) {
            return ResponseEntity.status(401).body("Invalid credentials or role");
        }

        System.out.println("User Found: " + user.getRole());
        return ResponseEntity.ok(user);
    }
}
