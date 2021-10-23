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

    /*
    * Время фазы = tOsn + tProm >= tMin + tProm
    * Время цикла = Сумма времени всех фаз (Phase1+Phase2+Phase3)
    * */

    private Long id; //номер фазы

    @JsonProperty("t_osn")
    private Long tOsn; //время основного горения зеленого

    @JsonProperty("t_prom")
    private Long tProm; //время промежуточного такта

    @JsonProperty("t_min")
    private Long tMin; //время минимального горения зеленого

    @JsonProperty("is_hidden")
    private Boolean isHidden; //признак специальной фазы (у участников таких фаз нет)

    private Integer[] directions; //номера направлений, учавствующих в фазе
}
