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

    private Long prevStatus; //кодовое обознание предыдущего статуса

    private Long nextStatus; //кодовое обозначение следующего статуса

    private Long source;

    private Long mode; //кодовое обозначение режима

    private Long prevPhaseID; //номер предыдущей фазы сек. назад

    private Long nextPhaseID; //номер следующей фазы через сек

    private Long prevProgramID; //номер программы на предыдущем цикле

    private Long nextProgramID; //номер программы на следующей цикле

}
