package com.se192987.webdahoacuong.service;

import com.se192987.webdahoacuong.entity.Project;
import com.se192987.webdahoacuong.exception.ResourceNotFoundException;
import com.se192987.webdahoacuong.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private FileStorageService fileStorageService;

    public Project createProject(String title, MultipartFile image) {
        String imageUrl = null;
        if (image != null && !image.isEmpty()) {
            imageUrl = fileStorageService.storeFile(image);
        }

        Project project = Project.builder()
                .title(title)
                .imageUrl(imageUrl)
                .build();
        return projectRepository.save(project);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public void deleteProject(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id " + id));
        projectRepository.delete(project);
    }
}
