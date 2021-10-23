package ru.jat.trafficcontrol.model;

import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "road_controller_program")
public class RoadControllerProgramEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "road_controller_id")
    private long roadControllerId;
    @Column(name = "program_name")
    private String programName;
    @Column(name = "program_type")
    private String programType;
    @Column(name = "first_phase_duration")
    private long firstPhaseDuration;
    @Column(name = "second_phase_duration")
    private long secondPhaseDuration;
    @Column(name = "third_phase_duration")
    private long thirdPhaseDuration;
    @Column(name = "weight")
    private int weight;
    @Column(name = "anomaly")
    private boolean anomaly;
    @UpdateTimestamp
    @Column(name = "updated")
    private Timestamp updated;
}
