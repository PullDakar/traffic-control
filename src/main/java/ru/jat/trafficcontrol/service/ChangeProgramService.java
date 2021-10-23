package ru.jat.trafficcontrol.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.jat.trafficcontrol.model.CustomPhaseProgramRequest;
import ru.jat.trafficcontrol.repository.ChangeProgramOrderRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChangeProgramService {

    private final ChangeProgramOrderRepository changeProgramOrderRepository;
    private final Map<Long, CustomPhaseProgramRequest> customPhaseProgramRequestMap;
    private final TrafficLightService trafficLightService;

    @Scheduled(fixedRate = 1_000)
    public void changeProgramSchedule33051() {
        changeProgram(33051L);
    }

    @Scheduled(fixedRate = 1_000)
    public void changeProgramSchedule33052() {
        changeProgram(33052L);
    }

    @Scheduled(fixedRate = 1_000)
    public void changeProgramSchedule33053() {
        changeProgram(33053L);
    }

    @Scheduled(fixedRate = 1_000)
    public void changeProgramSchedule33054() {
        changeProgram(33054L);
    }

    @Scheduled(fixedRate = 1_000)
    public void changeProgramSchedule33055() {
        changeProgram(33055L);
    }

    @Scheduled(fixedRate = 1_000)
    public void changeProgramSchedule33056() {
        changeProgram(33056L);
    }

    @Scheduled(fixedRate = 1_000)
    public void changeProgramSchedule33057() {
        changeProgram(33057L);
    }

    @Scheduled(fixedRate = 1_000)
    public void changeProgramSchedule33058() {
        changeProgram(33058L);
    }

    @Scheduled(fixedRate = 1_000)
    public void changeProgramSchedule33059() {
        changeProgram(33059L);
    }

    @Scheduled(fixedRate = 1_000)
    public void changeProgramSchedule33060() {
        changeProgram(33060L);
    }

    private void changeProgram(Long id) {
        var changeProgramOrder = changeProgramOrderRepository.findTop1ByRoadControllerIdOrderByChangeTime(id).orElse(null);
        if (changeProgramOrder != null) {
            var currentTime = Timestamp.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
            if (changeProgramOrder.getChangeTime().after(currentTime)) {
                var customPhaseProgramResponse = trafficLightService.changeProgram(id, customPhaseProgramRequestMap.get(changeProgramOrder.getNewProgramId()));
                log.info(customPhaseProgramResponse.toString());
                if ("OK".equalsIgnoreCase(customPhaseProgramResponse.getStatus())) {
                    changeProgramOrderRepository.delete(changeProgramOrder);
                }
            } else {
                changeProgramOrderRepository.delete(changeProgramOrder);
            }
        }

    }
}
