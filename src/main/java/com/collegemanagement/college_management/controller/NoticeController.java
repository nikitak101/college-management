package com.collegemanagement.college_management.controller;

import com.collegemanagement.college_management.model.Notice;
import com.collegemanagement.college_management.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/notices")
@CrossOrigin(origins = "http://localhost:3000") // ✅ Restrict to frontend URL
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    // ✅ API to upload a notice (Supports both text & file-based content)
    @PostMapping("/add")
    public ResponseEntity<String> uploadNotice(
            @RequestParam Long facultyId,
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String postedBy,
            @RequestParam(required = false) MultipartFile content,  // ✅ Changed "file" to "content"
            @RequestParam(required = false) String textContent) {  // ✅ Allow plain text notices

        String response = noticeService.uploadNotice(facultyId, title, description, postedBy, content, textContent);
        return ResponseEntity.ok(response);
    }

    // ✅ API to fetch all notices
    @GetMapping("/all")
    public List<Notice> getAllNotices() {
        return noticeService.getAllNotices();
    }
}
