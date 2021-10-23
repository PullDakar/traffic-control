package ru.jat.trafficcontrol.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.jat.trafficcontrol.model.CustomPhaseProgramRequest;
import ru.jat.trafficcontrol.model.Phase;

import java.util.ArrayList;

@Disabled
@Slf4j
@SpringBootTest
class TrafficLightServiceTest {

    @Autowired
    TrafficLightService trafficLightService;

    /*
     * Время фазы = tOsn + tProm >= tMin + tProm
     * Время цикла = Сумма времени всех фаз (Phase1+Phase2+Phase3)
     * */

    @Test
    void changeProgram() {
        var phases = new ArrayList<Phase>();
        var phase1 = Phase.builder()
                .id(1L)
                .isHidden(false)
                .tMin(10L)
                .tOsn(40L)
                .tProm(5L)
                .directions(null)
                .build();
        //Время фазы = 45 или 15


        var phase2 = Phase.builder()
                .id(2L)
                .isHidden(false)
                .tMin(9L)
                .tOsn(15L)
                .tProm(5L)
                .directions(null)
                .build();
        //Время фазы = 20 или 14

        var phase3 = Phase.builder()
                .id(3L)
                .isHidden(false)
                .tMin(20L)
                .tOsn(25L)
                .tProm(5L)
                .directions(null)
                .build();
        //Время фазы = 30 или 25

        phases.add(phase1);
        phases.add(phase2);
        phases.add(phase3);

        var request = CustomPhaseProgramRequest.builder()
                .phases(phases)
                .startPhaseId(1L)
                .timeStartSync(1635003300L)
                .tCycle(95L)
                .build();
        //Все фазы = 95 штатно.

        var response = trafficLightService.changeProgram(33051L, request);
        log.info(response.toString());
    }
}