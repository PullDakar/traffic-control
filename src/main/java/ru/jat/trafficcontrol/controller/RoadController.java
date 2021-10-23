package ru.jat.trafficcontrol.controller;

import lombok.RequiredArgsConstructor;
import org.awaitility.Awaitility;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
public class RoadController {
    private final RestTemplate restTemplate;

    @PostMapping("/program")
    public void changeProgram(@RequestParam Long roadControllerId) {

    }
}
