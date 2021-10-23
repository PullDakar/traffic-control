package ru.jat.trafficcontrol.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.jat.trafficcontrol.model.MonitoringEntity;
import ru.jat.trafficcontrol.model.TrafficLightStatus;
import ru.jat.trafficcontrol.repository.MonitoringRepository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrafficLightService {

    private final RestTemplate restTemplate;
    private final MonitoringRepository monitoringRepository;
    private final ApplicationEventPublisher publisher;
    private final Map<Long, TrafficLightStatus> trafficLightStatusMap = new ConcurrentHashMap<>(10);

    @Async
    @Scheduled(fixedDelay = 1_000)
    public void schedule33051() {
        saveTrafficLightStatus(33051L);
    }

    @Async
    @Scheduled(fixedDelay = 1_000)
    public void schedule33052() {
        saveTrafficLightStatus(33052L);
    }

    @Async
    @Scheduled(fixedDelay = 1_000)
    public void schedule33053() {
        saveTrafficLightStatus(33053L);
    }

    @Async
    @Scheduled(fixedDelay = 1_000)
    public void schedule33054() {
        saveTrafficLightStatus(33054L);
    }

    @Async
    @Scheduled(fixedDelay = 1_000)
    public void schedule33055() {
        saveTrafficLightStatus(33055L);
    }

    @Async
    @Scheduled(fixedDelay = 1_000)
    public void schedule33056() {
        saveTrafficLightStatus(33056L);
    }

    @Async
    @Scheduled(fixedDelay = 1_000)
    public void schedule33057() {
        saveTrafficLightStatus(33057L);
    }

    @Async
    @Scheduled(fixedDelay = 1_000)
    public void schedule33058() {
        saveTrafficLightStatus(33058L);
    }

    @Async
    @Scheduled(fixedDelay = 1_000)
    public void schedule33059() {
        saveTrafficLightStatus(33059L);
    }

    @Async
    @Scheduled(fixedDelay = 1_000)
    public void schedule33060() {
        saveTrafficLightStatus(33060L);
    }

    private void saveTrafficLightStatus(Long id) {
        var trafficLightStatus = getTrafficLightStatus(id);
        log.info(trafficLightStatus.toString());
        if (trafficLightStatusMap.containsKey(id)) {
            var previous = trafficLightStatusMap.get(id);
            if (!trafficLightStatus.getCurrentPhaseId().equals(previous.getCurrentPhaseId())) {
                var monitoringEntity = MonitoringEntity.builder()
                        .phaseId(previous.getCurrentPhaseId())
                        .programId(trafficLightStatus.getCurrentProgramId() != null ? trafficLightStatus.getCurrentProgramId() : -1)
                        .phaseDuration(trafficLightStatus.getTime() - previous.getTime())
                        .roadControllerId(id)
                        .build();
                monitoringRepository.save(monitoringEntity);
                publisher.publishEvent(monitoringEntity);
                trafficLightStatusMap.put(id, trafficLightStatus);
                log.info(monitoringEntity.toString());
            }
        } else {
            trafficLightStatusMap.put(id, trafficLightStatus);
        }
    }

    private TrafficLightStatus getTrafficLightStatus(Long id) {
        return restTemplate.getForObject("/" + id + "/status", TrafficLightStatus.class);
    }
}
