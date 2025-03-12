package com.collegemanagement.college_management.dto;

public class LoginRequest {
    private String rollNo;
    private String password;
    private String role;  // ✅ Role is sent as String from frontend

    public String getRollNo() { return rollNo; }
    public void setRollNo(String rollNo) { this.rollNo = rollNo; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
