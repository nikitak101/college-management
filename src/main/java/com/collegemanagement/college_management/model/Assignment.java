package com.collegemanagement.college_management.model;

import jakarta.persistence.*;

@Entity
@Table(name = "assignments")
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long facultyId;
    private String title;
    private String description;
    private String dueDate;
    private String filePath; // Store only filename
    private String fileUrl;  // Full file URL

    public Assignment() {}

    public Assignment(Long facultyId, String title, String description, String dueDate, String filePath) {
        this.facultyId = facultyId;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.filePath = filePath;
        this.fileUrl = "http://localhost:8090/uploads/" + filePath;
    }

    // ✅ Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getFacultyId() { return facultyId; }
    public void setFacultyId(Long facultyId) { this.facultyId = facultyId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getDueDate() { return dueDate; }
    public void setDueDate(String dueDate) { this.dueDate = dueDate; }

    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }

    public String getFileUrl() { return fileUrl; }
    public void setFileUrl(String fileUrl) { this.fileUrl = fileUrl; }
}
