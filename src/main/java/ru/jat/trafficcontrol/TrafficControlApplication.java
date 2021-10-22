package ru.jat.trafficcontrol;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@EnableAsync
@EnableScheduling
@SpringBootApplication
@RequiredArgsConstructor
public class TrafficControlApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrafficControlApplication.class, args);
    }

}
