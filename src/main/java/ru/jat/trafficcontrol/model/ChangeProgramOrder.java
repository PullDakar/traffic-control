package ru.jat.trafficcontrol.model;

import javax.persistence.*;

@Entity
@Table(name = "change_program_queue")
public class ChangeProgramOrder {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "road_controller_id")
    private long roadControllerId;

    @Column(name = "new_program_id")
    private long newProgramId;
}
