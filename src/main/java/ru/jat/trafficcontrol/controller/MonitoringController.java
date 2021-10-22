package ru.jat.trafficcontrol.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.jat.trafficcontrol.model.MonitoringEntity;
import ru.jat.trafficcontrol.repository.MonitoringRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MonitoringController {
    private final MonitoringRepository monitoringRepository;

    @GetMapping("/monitoring")
    public ResponseEntity<List<MonitoringEntity>> getMonitoringData(String roadControllerId) {
        return null;
    }
}
