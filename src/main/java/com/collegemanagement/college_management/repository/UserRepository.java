package com.collegemanagement.college_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.collegemanagement.college_management.model.User;
import com.collegemanagement.college_management.model.Role;  // ✅ Import Role Enum

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.rollNumber = :rollNumber AND u.password = :password AND u.role = :role")
    User findByCredentials(@Param("rollNumber") String rollNumber,
                           @Param("password") String password,
                           @Param("role") Role role);  // ✅ Use Role Enum
}
