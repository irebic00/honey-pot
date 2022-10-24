package com.honey.honeypot.service;

import com.honey.honeypot.repository.geo.GeoLocation;
import com.honey.honeypot.repository.geo.GeoLocationRepository;
import com.honey.honeypot.service.dto.GeoLocationEntry;
import com.honey.honeypot.service.mapper.GeoLocationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.h2.tools.Script;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.File;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class GeoLocationService {

    private final DataSource dataSource;

    private final GeoLocationRepository geoLocationRepository;
    private final GeoLocationMapper geoLocationMapper;

    public List<GeoLocationEntry> getAllGeoLocationEntries() {
        return geoLocationRepository.findAll().stream().map(geoLocationMapper::toGeoLocationEntry).collect(Collectors.toList());
    }

    public void insertNew(String ipAddress, Double longitude, Double latitude, String errorMessage) {
        GeoLocation geoLocation = GeoLocation.builder()
                .ipAddress(ipAddress)
                .longitude(longitude)
                .latitude(latitude)
                .errorMessage(errorMessage)
                .build();
        geoLocationRepository.save(geoLocation);
    }

    public File backup() {
        String fileName = "backup-" + LocalDate.now() + ".sql";
        try (Connection connection = dataSource.getConnection()) {
            Script.process(connection, fileName, "", "");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new File(fileName);
    }

    public void updateOrInsert(String ipAddress, Double longitude, Double latitude) {
        updateOrInsert(ipAddress, longitude, latitude, null, null);
    }

    public void updateOrInsert(String ipAddress, String error, String message) {
        updateOrInsert(ipAddress, null, null, error, message);
    }

    private void updateOrInsert(String ipAddress, Double longitude, Double latitude, String error, String message) {
        String errorMessage = null;
        if (error != null && message != null) {
            errorMessage = error + ": " + message;
        }

        List<GeoLocation> geoLocations = geoLocationRepository.findByIpAddressEquals(ipAddress);

        if (geoLocations == null || geoLocations.isEmpty()) {
            insertNew(ipAddress, longitude, latitude, errorMessage);
            return;
        }

        boolean anyFullInitialized = geoLocations.stream().anyMatch(gl -> gl.getLatitude() != null && gl.getLongitude() != null);
        if (anyFullInitialized) {
            return;
        }

        GeoLocation geoLocation;
        if (errorMessage != null) {
            geoLocation = geoLocations.get(geoLocations.size() - 1);
        } else {
            geoLocation = geoLocations.get(0);
        }
        geoLocation.setLatitude(latitude);
        geoLocation.setLongitude(longitude);
        geoLocation.setErrorMessage(errorMessage);
        geoLocationRepository.save(geoLocation);
    }
}
