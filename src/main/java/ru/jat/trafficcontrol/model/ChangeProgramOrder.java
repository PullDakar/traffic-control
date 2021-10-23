package ru.jat.trafficcontrol.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:sss")
    @Column(name = "change_time")
    private Timestamp changeTime;
}
