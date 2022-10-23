package com.honey.honeypot.repository.geo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeoLocationRepository extends JpaRepository<GeoLocation, Long> {
    List<GeoLocation> findByIpAddressEquals(String ipAddress);
}
