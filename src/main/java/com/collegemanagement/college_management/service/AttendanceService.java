package com.collegemanagement.college_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.collegemanagement.college_management.repository.AttendanceRepository;
import com.collegemanagement.college_management.model.Attendance;
import java.util.List;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    public List<Attendance> getAllAttendanceRecords() {
        return attendanceRepository.findAll();
    }
}
