package ru.jat.trafficcontrol.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ProgramPhaseId.class)
@Table(name = "program_phase")
public class ProgramPhaseEntity {
    @Id
    @Column(name = "road_controller_id")
    private long roadControllerId;
    @Id
    @Column(name = "program_id")
    private long programId;
    @Id
    @Column(name = "phase_id")
    private long phaseId;
    @Column(name = "phase_duration")
    private long phaseDuration;
}
