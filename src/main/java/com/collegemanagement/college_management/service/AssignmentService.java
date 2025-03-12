package com.collegemanagement.college_management.service;

import com.collegemanagement.college_management.model.Assignment;
import com.collegemanagement.college_management.repository.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.*;
import java.util.List;

@Service
public class AssignmentService {

    private static final String UPLOAD_DIR = "uploads/";

    @Autowired
    private AssignmentRepository assignmentRepository;

    public String uploadAssignment(Long facultyId, String title, String description,
                                   String dueDate, MultipartFile file) {
        try {
            // ✅ Ensure upload directory exists
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // ✅ Generate unique filename
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(UPLOAD_DIR, fileName);
            Files.write(filePath, file.getBytes());

            // ✅ Save assignment in the database
            Assignment assignment = new Assignment();
            assignment.setFacultyId(facultyId);
            assignment.setTitle(title);
            assignment.setDescription(description);
            assignment.setDueDate(dueDate);
            assignment.setFilePath(fileName);
            assignment.setFileUrl("http://localhost:8090/uploads/" + fileName); // ✅ Store full URL

            assignmentRepository.save(assignment);
            return "Assignment uploaded successfully: " + fileName;

        } catch (Exception e) {
            return "Error uploading assignment: " + e.getMessage();
        }
    }

    public List<Assignment> getAllAssignments() {
        return assignmentRepository.findAll();
    }

    public void saveAssignment(Assignment assignment) {
        assignmentRepository.save(assignment);
    }
}
