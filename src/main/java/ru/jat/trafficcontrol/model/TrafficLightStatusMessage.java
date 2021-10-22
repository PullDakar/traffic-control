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
public class TrafficLightStatusMessage {

    private UUID uuid;

    @JsonProperty("rc_id")
    private Long rcId;

    private Long prevStatus;

    private Long nextStatus;

    private Long source;

    private Long mode;

    private Long prevPhaseID;

    private Long nextPhaseID;

    private Long prevProgramID;

    private Long nextProgramID;

}
