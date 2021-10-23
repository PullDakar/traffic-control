package ru.jat.trafficcontrol.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

/**
 * POJO представляющее запрос для установки индивидуальной фазовой программы для выбранного дорожного контроллера по его идентификатору
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomPhaseProgramRequest {

    /**
     * фаза, с которой начинается цикл
     */
    @JsonProperty("start_phase_id")
    private Long startPhaseId;

    /**
     * время, когда необходимо включить первую фазу новой программы
     */
    @JsonProperty("time_start_sync")
    private Long timeStartSync;

    /**
     * время цикла
     */
    @JsonProperty("t_cycle")
    private Long tCycle;

    /**
     * фазы данной программы
     */
    private List<Phase> phases;
}
