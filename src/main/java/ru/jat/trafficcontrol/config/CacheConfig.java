package ru.jat.trafficcontrol.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.jat.trafficcontrol.model.CustomPhaseProgramRequest;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Класс, конфигурирующий кэш программ, необходимый для отправки в очередь на исполнение изменений программы
 */
@Configuration
public class CacheConfig {

    /**
     * @return кэш программ для взаимодействия с очередью программ
     */
    @Bean
    public Map<Integer, CustomPhaseProgramRequest> customPhaseProgramRequestMap() {
        return new ConcurrentHashMap<>();
    }
}
