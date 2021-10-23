package ru.jat.trafficcontrol.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

/**
 * Класс, конфигурирующий {@link RestTemplate} необходимый для взаимодействия c API https://via-dolorosa.ru/api/
 */
@Configuration
public class WebConfig {

    /**
     * Метод, конфигурирующий {@link RestTemplate}
     *
     * @return restTemplate с базовым url, соответсвующим API АСУДД
     */
    @Bean
    public RestTemplate restTemplate() {
        final var restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory("https://api.via-dolorosa.ru/rc"));
        return restTemplate;
    }
}
