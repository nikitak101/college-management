package com.collegemanagement.college_management.controller;

import com.collegemanagement.college_management.model.Assignment;
import com.collegemanagement.college_management.service.AssignmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/assignments")
@CrossOrigin(origins = "http://localhost:3000") // ✅ Allow frontend access
public class AssignmentController {

    private static final Logger logger = LoggerFactory.getLogger(AssignmentController.class);
    private static final String BASE_FILE_URL = "http://localhost:8090/uploads/";

    @Autowired
    private AssignmentService assignmentService;

    // ✅ Fetch all assignments
    @GetMapping
    public ResponseEntity<List<Assignment>> getAllAssignments() {
        List<Assignment> assignments = assignmentService.getAllAssignments();

        // ✅ Ensure fileUrl is correctly formatted
        for (Assignment assignment : assignments) {
            if (assignment.getFilePath() != null) {
                assignment.setFileUrl(BASE_FILE_URL + assignment.getFilePath()); // ✅ Only filename
            }
        }
        return ResponseEntity.ok(assignments);
    }

    // ✅ Upload an assignment
    @PostMapping("/upload")
    public ResponseEntity<String> uploadAssignment(
            @RequestParam("facultyId") Long facultyId,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("dueDate") String dueDate,
            @RequestParam("file") MultipartFile file) {

        logger.info("Received assignment upload request: facultyId={}, title={}, dueDate={}",
                facultyId, title, dueDate);

        if (file.isEmpty()) {
            logger.error("Error: File is missing.");
            return ResponseEntity.badRequest().body("Error: File is required.");
        }

        String response = assignmentService.uploadAssignment(facultyId, title, description, dueDate, file);
        return ResponseEntity.ok(response);
    }
}

// ✅ Separate FileController to serve files
@RestController
@RequestMapping("/uploads")
class FileController {

    private static final String UPLOAD_DIR = "uploads"; // ✅ Ensure this matches your file storage location

    @GetMapping("/{filename:.+}")
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        try {
            Path filePath = Paths.get(UPLOAD_DIR).resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists() || !resource.isReadable()) {
                throw new RuntimeException("File not found: " + filename);
            }

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);

        } catch (Exception e) {
            return ResponseEntity.status(404).body(null);
        }
    }
}
