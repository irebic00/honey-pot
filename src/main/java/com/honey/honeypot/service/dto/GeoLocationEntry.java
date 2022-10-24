package com.honey.honeypot.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeoLocationEntry {
    private String ipAddress;
    private Double longitude;
    private Double latitude;
    private String errorMessage;
}
