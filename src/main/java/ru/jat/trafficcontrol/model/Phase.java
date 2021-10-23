package ru.jat.trafficcontrol.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * POJO описывающий фазу программы
 *
 * Время фазы = tOsn + tProm >= tMin + tProm
 * Время цикла = Сумма времени всех фаз (Phase1+Phase2+Phase3)
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Phase {
    /**
     * номер фазы
     */
    private Long id;

    /**
     * время основного горения зеленого
     */
    @JsonProperty("t_osn")
    private Long tOsn;

    /**
     * время промежуточного такта
     */
    @JsonProperty("t_prom")
    private Long tProm;

    /**
     * время минимального горения зеленого
     */
    @JsonProperty("t_min")
    private Long tMin;

    /**
     * признак специальной фазы (у участников таких фаз нет)
     */
    @JsonProperty("is_hidden")
    private Boolean isHidden;

    /**
     * номера направлений, участвующих в фазе
     */
    private Integer[] directions;
}
