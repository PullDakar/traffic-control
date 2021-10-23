package ru.jat.trafficcontrol.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * POJO для сигналов мониторинга, являющихся кандидатами-программами
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ProgramCandidate {
    /**
     * Идентификатор контроллера
     */
    private final long roadControllerId;
    /**
     * Массив, где "индекс + 1" - номер фазы. Значение по этому индексу - длителньость фазы
     */
    private final long[] phases = new long[3];
    /**
     * Вес кандидата в программу
     */
    private int weight = 1;
}
