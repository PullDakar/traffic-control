package ru.jat.trafficcontrol.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Phase {

    private Long id;

    @JsonProperty("t_osn")
    private Long tOsn;

    @JsonProperty("t_prom")
    private Long tProm;

    @JsonProperty("t_min")
    private Long tMin;

    @JsonProperty("is_hidden")
    private Boolean isHidden;

    private Integer[] directions;
}
