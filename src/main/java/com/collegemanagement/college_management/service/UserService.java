package com.collegemanagement.college_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.collegemanagement.college_management.model.User;
import com.collegemanagement.college_management.model.Role;
import com.collegemanagement.college_management.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserByCredentials(String rollNo, String password, String role) {
        Role userRole;
        try {
            userRole = Role.valueOf(role.toUpperCase()); // ✅ Convert String to Enum
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid role received: " + role);
            return null;  // Return null if role is not valid (causes 401 Unauthorized)
        }

        return userRepository.findByCredentials(rollNo, password, userRole);
    }
}
