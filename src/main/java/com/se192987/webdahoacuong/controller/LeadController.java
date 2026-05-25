package com.se192987.webdahoacuong.controller;

import com.se192987.webdahoacuong.dto.ApiResponse;
import com.se192987.webdahoacuong.dto.LeadRequest;
import com.se192987.webdahoacuong.entity.Lead;
import com.se192987.webdahoacuong.enums.LeadStatus;
import com.se192987.webdahoacuong.service.LeadService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leads")
public class LeadController {

    @Autowired
    private LeadService leadService;

    @PostMapping
    public ResponseEntity<ApiResponse<Lead>> createLead(@Valid @RequestBody LeadRequest request) {
        Lead lead = leadService.createLead(request);
        return ResponseEntity.ok(new ApiResponse<>("Lead created successfully", lead));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Lead>>> getAllLeads() {
        List<Lead> leads = leadService.getAllLeads();
        return ResponseEntity.ok(new ApiResponse<>("Fetched all leads", leads));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ApiResponse<Lead>> updateLeadStatus(
            @PathVariable Long id,
            @RequestParam LeadStatus status) {
        Lead lead = leadService.updateLeadStatus(id, status);
        return ResponseEntity.ok(new ApiResponse<>("Lead status updated", lead));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteLead(@PathVariable Long id) {
        leadService.deleteLead(id);
        return ResponseEntity.ok(new ApiResponse<>("Lead deleted successfully", null));
    }
}
