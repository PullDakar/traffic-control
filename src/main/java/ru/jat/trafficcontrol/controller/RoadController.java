package ru.jat.trafficcontrol.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.jat.trafficcontrol.model.ChangeProgramOrder;
import ru.jat.trafficcontrol.model.CustomPhaseProgramRequest;
import ru.jat.trafficcontrol.repository.ChangeProgramOrderRepository;
import ru.jat.trafficcontrol.service.TrafficLightService;

import java.sql.Timestamp;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Контроллер для взаимодействия с дорожной инфраструктурой
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class RoadController {

    private final ChangeProgramOrderRepository changeProgramOrderRepository;
    private final Map<Integer, CustomPhaseProgramRequest> customPhaseProgramRequestMap;
    private final TrafficLightService trafficLightService;
    private final AtomicInteger programIdAtomic = new AtomicInteger(1);

    @PostMapping("/program/{programId}/apply")
    public ResponseEntity<Void> changeProgram(@RequestParam Long roadControllerId, @RequestParam Timestamp changeTime, @PathVariable("programId") Integer programId) {
        if (customPhaseProgramRequestMap.containsKey(programId)) {
            var changeProgramOrder = ChangeProgramOrder.builder()
                    .newProgramId(programId)
                    .changeTime(changeTime)
                    .roadControllerId(roadControllerId)
                    .build();
            changeProgramOrderRepository.save(changeProgramOrder);
            log.info(changeProgramOrder.toString());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/program")
    public ResponseEntity<Integer> addProgram(@RequestBody CustomPhaseProgramRequest customPhaseProgramRequest) {
        var programId = programIdAtomic.getAndIncrement();
        log.debug("Add program id: " + programId + customPhaseProgramRequest.toString());
        customPhaseProgramRequestMap.putIfAbsent(programId, customPhaseProgramRequest);
        return ResponseEntity.ok(programId);
    }

    @PostMapping("/program/setLocal")
    public ResponseEntity setLocal(@RequestParam Long roadControllerId) {
        return trafficLightService.setLocal(roadControllerId);
    }
}
