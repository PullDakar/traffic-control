package ru.jat.trafficcontrol.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.jat.trafficcontrol.model.MonitoringEntity;
import ru.jat.trafficcontrol.repository.MonitoringRepository;

import java.util.List;

/**
 * Контроллер для взаимодействия с таблицей мониторинга
 */
@RestController
@RequiredArgsConstructor
public class MonitoringController {
    /**
     * Абстракция для взаимодействия с БД с таблицей {@link MonitoringEntity}
     */
    private final MonitoringRepository monitoringRepository;

    /**
     * Получение полного набора информации из таблицы мониторинга
     *
     * @return полный набора информации из таблицы мониторинга
     */
    @GetMapping("/monitoring")
    @Operation(summary = "Получение полного набора информации из таблицы мониторинга")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Выполнено успешно"),
    })
    public ResponseEntity<List<MonitoringEntity>> getMonitoringData() {
        return ResponseEntity
                .ok(monitoringRepository.findAll());
    }
}
