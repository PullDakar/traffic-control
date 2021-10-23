package ru.jat.trafficcontrol.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * POJO отражающее сущность в БД, обеспечивающую CRUD операции для мониторинга ДК
 */
@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "monitoring")
public class MonitoringEntity {
    /**
     *  Идентификатор записи мониторинга
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Идентификатор контроллера
     */
    @Column(name = "road_controller_id")
    private long roadControllerId;

    /**
     * идентификатор фазы
     */
    @Column(name = "phase_id")
    private long phaseId;

    /**
     * идентификатор программы
     */
    @Column(name = "program_id")
    private long programId;

    /**
     * Временная метка изменения фазы
     */
    @CreationTimestamp
    @Column(name = "switch_moment")
    private Timestamp switchMoment;

    /**
     * Длительность фазы
     */
    @Column(name = "phase_duration")
    private long phaseDuration;
}
