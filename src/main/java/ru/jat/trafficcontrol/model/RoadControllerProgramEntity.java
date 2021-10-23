package ru.jat.trafficcontrol.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(RoadControllerProgramId.class)
@Table(name = "road_controller_program")
public class RoadControllerProgramEntity {
    @Id
    @Column(name = "id")
    private long id;
    @Id
    @Column(name = "road_controller_id")
    private long roadControllerId;
    @Column(name = "program_name")
    private String programName;
    @Column(name = "phase_count")
    private long phaseCount;
}
