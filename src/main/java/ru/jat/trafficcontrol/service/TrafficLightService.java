package ru.jat.trafficcontrol.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.jat.trafficcontrol.model.CustomPhaseProgramRequest;
import ru.jat.trafficcontrol.model.CustomPhaseProgramResponse;
import ru.jat.trafficcontrol.model.MonitoringEntity;
import ru.jat.trafficcontrol.model.TrafficLightStatus;
import ru.jat.trafficcontrol.repository.MonitoringRepository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Сервис наполняющий таблицу мониторинга необходимой информацией
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TrafficLightService {
    /**
     * Служебный класс для взаимодействия с внешним API
     */
    private final RestTemplate restTemplate;
    /**
     * Абстракция для взаимодействия с БД с таблицей {@link MonitoringEntity}
     */
    private final MonitoringRepository monitoringRepository;
    /**
     * Служебный класс для публикации событий приложения
     */
    private final ApplicationEventPublisher publisher;
    /**
     *
     */
    private final Map<Long, TrafficLightStatus> trafficLightStatusMap = new ConcurrentHashMap<>(10);

    /**
     * Шедулер, наполняющий таблицу мониторинга в части ДК с идентификатором 33051
     */
    @Async
    @Scheduled(fixedRate = 500)
    public void schedule33051() {
        saveTrafficLightStatus(33051L);
    }

    /**
     * Шедулер, наполняющий таблицу мониторинга в части ДК с идентификатором 33052
     */
    @Async
    @Scheduled(fixedRate = 500)
    public void schedule33052() {
        saveTrafficLightStatus(33052L);
    }

    /**
     * Шедулер, наполняющий таблицу мониторинга в части ДК с идентификатором 33053
     */
    @Async
    @Scheduled(fixedRate = 500)
    public void schedule33053() {
        saveTrafficLightStatus(33053L);
    }

    /**
     * Шедулер, наполняющий таблицу мониторинга в части ДК с идентификатором 33054
     */
    @Async
    @Scheduled(fixedRate = 500)
    public void schedule33054() {
        saveTrafficLightStatus(33054L);
    }

    /**
     * Шедулер, наполняющий таблицу мониторинга в части ДК с идентификатором 33055
     */
    @Async
    @Scheduled(fixedRate = 500)
    public void schedule33055() {
        saveTrafficLightStatus(33055L);
    }

    /**
     * Шедулер, наполняющий таблицу мониторинга в части ДК с идентификатором 33056
     */
    @Async
    @Scheduled(fixedRate = 500)
    public void schedule33056() {
        saveTrafficLightStatus(33056L);
    }

    /**
     * Шедулер, наполняющий таблицу мониторинга в части ДК с идентификатором 33057
     */
    @Async
    @Scheduled(fixedRate = 500)
    public void schedule33057() {
        saveTrafficLightStatus(33057L);
    }

    /**
     * Шедулер, наполняющий таблицу мониторинга в части ДК с идентификатором 33058
     */
    @Async
    @Scheduled(fixedRate = 500)
    public void schedule33058() {
        saveTrafficLightStatus(33058L);
    }

    /**
     * Шедулер, наполняющий таблицу мониторинга в части ДК с идентификатором 33059
     */
    @Async
    @Scheduled(fixedRate = 500)
    public void schedule33059() {
        saveTrafficLightStatus(33059L);
    }

    /**
     * Шедулер, наполняющий таблицу мониторинга в части ДК с идентификатором 33060
     */
    @Async
    @Scheduled(fixedRate = 500)
    public void schedule33060() {
        saveTrafficLightStatus(33060L);
    }

    /**
     * Сохранение в мониторинговую таблицу длительности перелюкчения фаз
     *
     * @param id - идентификатор ДК
     */
    private void saveTrafficLightStatus(Long id) {
        var trafficLightStatus = getTrafficLightStatus(id);
        //log.info(trafficLightStatus.toString());
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
          //      log.info(monitoringEntity.toString());
            }
        } else {
            trafficLightStatusMap.put(id, trafficLightStatus);
        }
    }

    public TrafficLightStatus getTrafficLightStatus(Long id) {
        return restTemplate.getForObject("/" + id + "/status", TrafficLightStatus.class);
    }

    public CustomPhaseProgramResponse changeProgram(Long id, CustomPhaseProgramRequest customPhaseProgramRequest) {
        return restTemplate.postForObject("/" + id + "/custom_phase_program", customPhaseProgramRequest, CustomPhaseProgramResponse.class);
    }

    public ResponseEntity setLocal(Long id) {
        return restTemplate.postForObject("/" + id + "/set_local", null, ResponseEntity.class);
    }

    public void holdPhase(Long id, Long phaseId) {
        restTemplate.postForObject("/" + id + "/keep_phase/" + phaseId, null, ResponseEntity.class);
    }

    public void unholdPhase(Long id) {
        restTemplate.postForObject("/" + id + "/cancel_keep_phase", null, ResponseEntity.class);
    }
}
