package com.honey.honeypot.service;

import com.honey.honeypot.repository.geo.GeoLocation;
import com.honey.honeypot.repository.geo.GeoLocationRepository;
import com.honey.honeypot.service.dto.GeoLocationEntry;
import com.honey.honeypot.service.mapper.GeoLocationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GeoLocationService {

    private final GeoLocationRepository geoLocationRepository;
    private final GeoLocationMapper geoLocationMapper;

    public List<GeoLocationEntry> getAllGeoLocationEntries() {
        return geoLocationRepository.findAll().stream().map(geoLocationMapper::toGeoLocationEntry).collect(Collectors.toList());
    }

    public void insertNew(String ipAddress, Double longitude, Double latitude) {
        GeoLocation geoLocation = GeoLocation.builder()
                .ipAddress(ipAddress)
                .longitude(longitude)
                .latitude(latitude)
                .build();
        geoLocationRepository.save(geoLocation);
    }

    public void updateOrInsert(String ipAddress, Double longitude, Double latitude) {
        List<GeoLocation> geoLocations = geoLocationRepository.findByIpAddressEquals(ipAddress);
        if (geoLocations != null && !geoLocations.isEmpty()) {
            geoLocations.forEach(geoLocation -> {
                geoLocation.setLatitude(latitude);
                geoLocation.setLongitude(longitude);
            });

            geoLocationRepository.saveAll(geoLocations);
        } else {
            insertNew(ipAddress, longitude, latitude);
        }
    }
}
