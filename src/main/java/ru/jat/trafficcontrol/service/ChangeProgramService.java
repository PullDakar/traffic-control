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

    @Scheduled(fixedDelay = 1_000)
    public void changeProgramSchedule33051() {
        changeProgram(33051L);
    }

    @Scheduled(fixedDelay = 1_000)
    public void changeProgramSchedule33052() {
        changeProgram(33052L);
    }

    private void changeProgram(Long id) {
        var changeProgramOrder = changeProgramOrderRepository.findTop1ByRoadControllerIdOrderByChangeTime(id).orElse(null);
        if (changeProgramOrder != null && customPhaseProgramRequestMap.containsKey(changeProgramOrder.getNewProgramId())) {
            var currentTime = Timestamp.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
            if (changeProgramOrder.getChangeTime().after(currentTime)) {
                var customPhaseProgramResponse = trafficLightService.changeProgram(id, customPhaseProgramRequestMap.get(changeProgramOrder.getNewProgramId()));
                if ("OK".equalsIgnoreCase(customPhaseProgramResponse.getStatus())) {
                    //applyProgam();
                    changeProgramOrderRepository.delete(changeProgramOrder);
                }
            } else {
                changeProgramOrderRepository.delete(changeProgramOrder);
            }
        }

    }
}
