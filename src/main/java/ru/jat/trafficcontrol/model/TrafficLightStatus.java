package ru.jat.trafficcontrol.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrafficLightStatus {

    private Long time;

    private UUID uuid;

    private String status;

    @JsonProperty("status_rc")
    private String statusRc;

    @JsonProperty("status_rc_desc")
    private String statusRcDesc;

    @JsonProperty("current_status")
    private String currentStatus;

    @JsonProperty("current_program_id")
    private Long currentProgramId;

    @JsonProperty("current_phase_id")
    private Long currentPhaseId;

    @JsonProperty("rc_response")
    private TrafficLightStatusResponse rcResponse;


}
