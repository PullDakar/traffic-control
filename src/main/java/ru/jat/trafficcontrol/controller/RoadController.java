package ru.jat.trafficcontrol.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Установка программы в очередь на применение в заданный момент времени на ДК")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Выполнено успешно"),
    })
    @PostMapping("/program/{programId}/apply")
    public ResponseEntity<Void> changeProgram(@Parameter(description = "Идентификатор ДК", example = "33051")
                                              @RequestParam Long roadControllerId,
                                              @Parameter(description = "Временная отметка изменения программы ДК", example = "2021-10-22 23:35:00")
                                              @RequestParam Timestamp changeTime,
                                              @Parameter(description = "Идентификатор программы", example = "1")
                                              @PathVariable("programId") Integer programId) {
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

    @Operation(summary = "Добавление новой программы в кэш программ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Выполнено успешно"),
    })
    @PostMapping("/program")
    public ResponseEntity<Integer> addProgram(@RequestBody CustomPhaseProgramRequest customPhaseProgramRequest) {
        var programId = programIdAtomic.getAndIncrement();
        log.info("Add program id: " + programId + " " + customPhaseProgramRequest.toString());
        customPhaseProgramRequestMap.put(programId, customPhaseProgramRequest);
        return ResponseEntity.ok(programId);
    }

    @Operation(summary = "Сброс программы на локальную на ДК")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Выполнено успешно"),
    })
    @PostMapping("/program/setLocal")
    public ResponseEntity setLocal(@Parameter(description = "Идентификатор ДК", example = "33051")
                                   @RequestParam Long roadControllerId) {
        return trafficLightService.setLocal(roadControllerId);
    }
}
