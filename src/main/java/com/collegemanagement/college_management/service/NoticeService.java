package com.collegemanagement.college_management.service;

import com.collegemanagement.college_management.model.Notice;
import com.collegemanagement.college_management.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Collections; // Import this

@Service
public class NoticeService {

    private static final String UPLOAD_DIR = "uploads/";
    private static final String BASE_URL = "http://localhost:8090/uploads/";

    @Autowired
    private NoticeRepository noticeRepository;

    public String uploadNotice(Long facultyId, String title, String description, String postedBy, MultipartFile content, String textContent) {
        try {
            String contentValue = null;

            // ✅ If a file is uploaded, save it
            if (content != null && !content.isEmpty()) {
                File uploadDir = new File(UPLOAD_DIR);
                if (!uploadDir.exists()) uploadDir.mkdirs();

                String fileName = System.currentTimeMillis() + "_" + content.getOriginalFilename();
                Path filePath = Paths.get(UPLOAD_DIR, fileName);
                Files.copy(content.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                contentValue = BASE_URL + fileName;  // ✅ Save file URL
            }
            // ✅ If no file but text content is provided, save text
            else if (textContent != null && !textContent.isEmpty()) {
                contentValue = textContent;  // ✅ Save plain text content
            }
            // ❌ If neither file nor text content is provided, return an error
            else {
                return "Error: Either a file or text content is required.";
            }

            // ✅ Save notice in database
            Notice notice = new Notice();
            notice.setFacultyId(facultyId);
            notice.setTitle(title);
            notice.setDescription(description);
            notice.setDatePosted(LocalDate.now().toString());
            notice.setContent(contentValue);
            notice.setPostedBy(postedBy);

            noticeRepository.save(notice);
            return "Notice uploaded successfully.";

        } catch (IOException e) {
            return "Error uploading notice: " + e.getMessage();
        }
    }

    public List<Notice> getAllNotices() {
        List<Notice> notices = noticeRepository.findAll();
        return (notices.isEmpty()) ? Collections.emptyList() : notices;
    }
}
