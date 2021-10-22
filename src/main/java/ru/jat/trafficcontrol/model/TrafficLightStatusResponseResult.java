package ru.jat.trafficcontrol.model;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrafficLightStatusResponseResult {

    private Long status;

    private String description;

}
