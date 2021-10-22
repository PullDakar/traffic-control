package ru.jat.trafficcontrol;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

@Slf4j
@EnableScheduling
@SpringBootApplication
@RequiredArgsConstructor
public class TrafficControlApplication {
    private final RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(TrafficControlApplication.class, args);
    }

//    @Scheduled(fixedDelay = 1_000)
    @Scheduled(fixedDelay = 1_000_000)
    public void schedule() {
        String resp = restTemplate.getForObject("/1/full_info", String.class);
        log.info(resp);
    }
}
