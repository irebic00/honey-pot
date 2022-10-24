package com.honey.honeypot.service.mapper;

import com.honey.honeypot.repository.geo.GeoLocation;
import com.honey.honeypot.service.dto.GeoLocationEntry;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public abstract class GeoLocationMapper {

    public abstract GeoLocationEntry toGeoLocationEntry(GeoLocation geoLocation);
}
