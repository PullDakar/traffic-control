package ru.jat.trafficcontrol.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "monitoring")
public class MonitoringEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "road_controller_id")
    private int roadControllerId;

    @CreationTimestamp
    @Column(name = "switch_moment")
    private Timestamp switchMoment;

    @Column(name = "phase_duration")
    private long phaseDuration;
}
