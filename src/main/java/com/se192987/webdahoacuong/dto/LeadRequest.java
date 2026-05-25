package com.se192987.webdahoacuong.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LeadRequest {
    @NotBlank(message = "Customer name is required")
    private String customerName;

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;
}
