package ru.jat.trafficcontrol;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import ru.jat.trafficcontrol.model.MonitoringEntity;
import ru.jat.trafficcontrol.repository.MonitoringRepository;

import java.sql.Date;

@Slf4j
@EnableScheduling
@SpringBootApplication
@RequiredArgsConstructor
public class TrafficControlApplication {
    private final MonitoringRepository monitoringRepository;

    public static void main(String[] args) {
        SpringApplication.run(TrafficControlApplication.class, args);
    }

//    @Scheduled(fixedDelay = 100000)
    public void schedule() {
//        MonitoringEntity monitoringEntity = new MonitoringEntity();
//        monitoringEntity.setPhaseDuration(12);
//        monitoringEntity.setRoadControllerId(1);
//        monitoringRepository.save(monitoringEntity);
    }
}
