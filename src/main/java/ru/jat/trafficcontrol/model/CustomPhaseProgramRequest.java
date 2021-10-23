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
    private Long startPhaseId; //фаза, с которой начинается цикл

    @JsonProperty("time_start_sync")
    private Long timeStartSync; //время, когда необходимо включить первую фазу новой программы

    @JsonProperty("t_cycle")
    private Long tCycle; //время цикла

    private List<Phase> phases; //фазы данной программы
}
