package com.honey.honeypot.repository.geo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Location", indexes = @Index(columnList = "ipAddress"))
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GeoLocation {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "IpAddress")
    private String ipAddress;

    @Column(name = "Longitude")
    private Double longitude;

    @Column(name = "Latitude")
    private Double latitude;
}
