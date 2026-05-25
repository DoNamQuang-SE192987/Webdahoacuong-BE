package com.se192987.webdahoacuong.service;

import com.se192987.webdahoacuong.dto.LeadRequest;
import com.se192987.webdahoacuong.entity.Lead;
import com.se192987.webdahoacuong.enums.LeadStatus;
import com.se192987.webdahoacuong.exception.ResourceNotFoundException;
import com.se192987.webdahoacuong.repository.LeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeadService {

    @Autowired
    private LeadRepository leadRepository;

    public Lead createLead(LeadRequest request) {
        Lead lead = Lead.builder()
                .customerName(request.getCustomerName())
                .phoneNumber(request.getPhoneNumber())
                .build();
        return leadRepository.save(lead);
    }

    public List<Lead> getAllLeads() {
        return leadRepository.findAll();
    }

    public Lead updateLeadStatus(Long id, LeadStatus status) {
        Lead lead = leadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lead not found with id " + id));
        lead.setStatus(status);
        return leadRepository.save(lead);
    }

    public void deleteLead(Long id) {
        Lead lead = leadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lead not found with id " + id));
        leadRepository.delete(lead);
    }
}
