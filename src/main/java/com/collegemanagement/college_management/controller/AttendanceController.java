package com.collegemanagement.college_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.collegemanagement.college_management.service.AttendanceService;
import com.collegemanagement.college_management.model.Attendance;
import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @GetMapping
    public List<Attendance> getAttendanceRecords() {
        return attendanceService.getAllAttendanceRecords();
    }
}
