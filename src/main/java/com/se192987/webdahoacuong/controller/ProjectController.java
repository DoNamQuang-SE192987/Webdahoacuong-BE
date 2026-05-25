package com.se192987.webdahoacuong.controller;

import com.se192987.webdahoacuong.dto.ApiResponse;
import com.se192987.webdahoacuong.entity.Project;
import com.se192987.webdahoacuong.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public ResponseEntity<ApiResponse<Project>> createProject(
            @RequestParam("title") String title,
            @RequestParam(value = "image", required = false) MultipartFile image) {
        
        Project project = projectService.createProject(title, image);
        return ResponseEntity.ok(new ApiResponse<>("Project created successfully", project));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Project>>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        return ResponseEntity.ok(new ApiResponse<>("Fetched all projects", projects));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.ok(new ApiResponse<>("Project deleted successfully", null));
    }
}
