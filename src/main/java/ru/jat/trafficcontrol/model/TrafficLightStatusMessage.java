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

    /**
     * кодовое обознание предыдущего статуса
     */
    private Long prevStatus;

    /**
     * кодовое обозначение следующего статуса
     */
    private Long nextStatus;

    private Long source;

    /**
     * кодовое обозначение режима
     */
    private Long mode;

    /**
     * номер предыдущей фазы сек. назад
     */
    private Long prevPhaseID;

    /**
     * номер следующей фазы через сек
     */
    private Long nextPhaseID;

    /**
     * номер программы на предыдущем цикле
     */
    private Long prevProgramID;

    /**
     * номер программы на следующей цикле
     */
    private Long nextProgramID;

}
