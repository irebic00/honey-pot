package com.honey.honeypot.service.mapper;

import com.honey.honeypot.repository.startup.Startup;
import com.honey.honeypot.service.dto.StartupEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public abstract class StartupMapper {

    public abstract StartupEntity toStartupEntry(Startup startup);
}
