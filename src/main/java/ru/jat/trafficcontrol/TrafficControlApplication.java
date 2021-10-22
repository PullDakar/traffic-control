package ru.jat.trafficcontrol;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;
import ru.jat.trafficcontrol.model.TrafficLightStatus;

@Slf4j
@EnableScheduling
@SpringBootApplication
@RequiredArgsConstructor
public class TrafficControlApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrafficControlApplication.class, args);
    }

}
