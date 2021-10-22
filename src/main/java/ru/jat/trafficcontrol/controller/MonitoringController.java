package ru.jat.trafficcontrol.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MonitoringController {
    @GetMapping("/monitoring")
    public void getMonitoringData() {

    }
}
