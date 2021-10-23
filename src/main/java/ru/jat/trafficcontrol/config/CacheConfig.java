package ru.jat.trafficcontrol.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.jat.trafficcontrol.model.CustomPhaseProgramRequest;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class CacheConfig {

    @Bean
    public Map<Long, CustomPhaseProgramRequest> customPhaseProgramRequestMap() {
        return new ConcurrentHashMap<>();
    }
}
