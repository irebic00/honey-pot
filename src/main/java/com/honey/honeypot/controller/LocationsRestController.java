package com.honey.honeypot.controller;

import com.honey.honeypot.service.GeoLocationService;
import com.honey.honeypot.service.dto.GeoLocationEntry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/locations")
@Slf4j
public class LocationsRestController {

    private final GeoLocationService geoLocationService;

    @GetMapping("/json")
    public ResponseEntity<List<GeoLocationEntry>> getLocations() {
        return ResponseEntity.ok(geoLocationService.getAllGeoLocationEntries());
    }

    @GetMapping("/create")
    public ResponseEntity<Void> createNew(HttpServletRequest request, @RequestParam Double longitude, @RequestParam Double latitude) {
        geoLocationService.updateOrInsert(request.getRemoteAddr(), longitude, latitude);
        return ResponseEntity.ok().build();
    }
}
