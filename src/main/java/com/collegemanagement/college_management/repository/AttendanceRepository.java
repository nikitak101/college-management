package com.collegemanagement.college_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.collegemanagement.college_management.model.Attendance;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
}
