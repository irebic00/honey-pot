package com.honey.honeypot.service;

import com.honey.honeypot.service.dto.GeoLocationEntry;
import com.honey.honeypot.repository.GeoLocation;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public abstract class GeoLocationMapper {

    public abstract GeoLocationEntry toGeoLocationEntry(GeoLocation geoLocation);
}
