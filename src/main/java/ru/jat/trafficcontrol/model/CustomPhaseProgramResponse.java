package ru.jat.trafficcontrol.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

/**
 * POJO представляющее ответ для установки индивидуальной фазовой программы для выбранного дорожного контроллера по его идентификатору
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomPhaseProgramResponse {
    /**
     *
     */
    private Long time;

    /**
     *
     */
    private UUID uuid;

    /**
     *
     */
    private String status;

    /**
     *
     */
    @JsonProperty("status_rc")
    private String statusRc;

    /**
     *
     */
    @JsonProperty("status_rc_desc")
    private String statusRcDesc;

    /**
     *
     */
    @JsonProperty("rc_response")
    private TrafficLightStatusResponse rcResponse;
}
