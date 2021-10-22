package ru.jat.trafficcontrol.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrafficLightStatusResponse {

    private UUID uuid;

    private TrafficLightStatusResponseResult result;

    @JsonProperty("status_msg")
    private TrafficLightStatusMessage statusMsg;

    private Long protocol;

    private Long type;

}
