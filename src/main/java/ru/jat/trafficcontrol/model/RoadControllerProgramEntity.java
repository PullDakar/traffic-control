package ru.jat.trafficcontrol.model;

import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * POJO отражающее сущность в БД, обеспечивающую CRUD операции для программ ДК
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "road_controller_program")
public class RoadControllerProgramEntity {
    /**
     * Идентификатор программы ДК
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    /**
     * Идентификатор ДК
     */
    @Column(name = "road_controller_id")
    private long roadControllerId;
    /**
     * Наименование программы
     */
    @Column(name = "program_name")
    private String programName;
    /**
     * Тип программы
     */
    @Column(name = "program_type")
    private String programType;
    /**
     * Длительность первой фазы
     */
    @Column(name = "first_phase_duration")
    private long firstPhaseDuration;
    /**
     * Длительность второй фазы
     */
    @Column(name = "second_phase_duration")
    private long secondPhaseDuration;
    /**
     * Длительность третьей фазы
     */
    @Column(name = "third_phase_duration")
    private long thirdPhaseDuration;
    /**
     * вес программы
     */
    @Column(name = "weight")
    private int weight;
    /**
     * флаг, показывающий является ли текущая программа аномалией
     */
    @Column(name = "anomaly")
    private boolean anomaly;
    /**
     * временная метка изменения программы
     */
    @UpdateTimestamp
    @Column(name = "updated")
    private Timestamp updated;
}
