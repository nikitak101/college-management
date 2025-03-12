package com.collegemanagement.college_management.model;

import jakarta.persistence.*;

@Entity
@Table(name = "notices")
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long facultyId;
    private String title;
    private String description;
    private String datePosted;
    private String content;  // ✅ Updated field name from "fileUrl" to "content"
    private String postedBy;

    public Notice() {}

    public Notice(Long facultyId, String title, String description, String datePosted, String content, String postedBy) {
        this.facultyId = facultyId;
        this.title = title;
        this.description = description;
        this.datePosted = datePosted;
        this.content = content;  // ✅ Use "content" instead of "fileUrl"
        this.postedBy = postedBy;
    }

    // ✅ Corrected Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getFacultyId() { return facultyId; }
    public void setFacultyId(Long facultyId) { this.facultyId = facultyId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getDatePosted() { return datePosted; }
    public void setDatePosted(String datePosted) { this.datePosted = datePosted; }

    public String getContent() { return content; }  // ✅ Getter for "content"
    public void setContent(String content) { this.content = content; }  // ✅ Setter for "content"

    public String getPostedBy() { return postedBy; }
    public void setPostedBy(String postedBy) { this.postedBy = postedBy; }
}
