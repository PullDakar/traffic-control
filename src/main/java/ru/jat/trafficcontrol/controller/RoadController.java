package ru.jat.trafficcontrol.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.jat.trafficcontrol.model.ChangeProgramOrder;
import ru.jat.trafficcontrol.model.CustomPhaseProgramRequest;
import ru.jat.trafficcontrol.repository.ChangeProgramOrderRepository;

import java.sql.Timestamp;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class RoadController {

    private final ChangeProgramOrderRepository changeProgramOrderRepository;
    private final Map<Long, CustomPhaseProgramRequest> customPhaseProgramRequestMap;

    @PostMapping("/program")
    public ResponseEntity<Void> changeProgram(@RequestParam Long roadControllerId, @RequestParam Timestamp changeTime, @RequestParam Long programId) {
        if (customPhaseProgramRequestMap.containsKey(programId)) {
            var changeProgramOrder = ChangeProgramOrder.builder()
                    .newProgramId(programId)
                    .changeTime(changeTime)
                    .roadControllerId(roadControllerId)
                    .build();
            changeProgramOrderRepository.save(changeProgramOrder);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/program/{id}")
    public void addProgram(@PathVariable Long id, @RequestBody CustomPhaseProgramRequest customPhaseProgramRequest) {
        customPhaseProgramRequestMap.putIfAbsent(id, customPhaseProgramRequest);
    }
}
