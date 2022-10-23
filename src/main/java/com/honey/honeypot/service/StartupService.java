package com.honey.honeypot.service;

import com.honey.honeypot.repository.startup.Startup;
import com.honey.honeypot.repository.startup.StartupRepository;
import com.honey.honeypot.service.dto.StartupEntity;
import com.honey.honeypot.service.mapper.StartupMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class StartupService {

    private final StartupRepository startupRepository;
    private final StartupMapper startupMapper;

    public void createStartup(LocalDate startedAt) {
        startupRepository.save(Startup.builder().startedAt(startedAt).build());
    }

    public StartupEntity getStartupEntity() {
        return startupMapper.toStartupEntry(startupRepository.findAll().get(0));
    }
}
