package ru.jat.trafficcontrol.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomPhaseProgramRequest {

    @JsonProperty("start_phase_id")
    private Long startPhaseId;

    @JsonProperty("time_start_sync")
    private Long timeStartSync;

    @JsonProperty("t_cycle")
    private Long tCycle;

    private List<Phase> phases;
}
