package ru.jat.trafficcontrol.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrafficLightInfo {

    private Long time;

    private Long id;

    @JsonProperty("rc_created_at")
    private LocalDateTime rcCreatedAt;

    @JsonProperty("rc_updated_at")
    private LocalDateTime rcUpdatedAt;

    /**
     * внешний номер объекта (в данном случае совпадает с id)
     */
    @JsonProperty("num_tl")
    private Long numTl;

    private String name;

    @JsonProperty("is_enabled")
    private Boolean isEnabled;

    @JsonProperty("is_alarmed")
    private Boolean isAlarmed;

    private String type;

    private String protocol;

    @JsonProperty("connStatus")
    private String conn_status;

    private String mode;

    @JsonProperty("program_id")
    private Long programId;

    @JsonProperty("keep_phase_id")
    private Long keepPhaseId;

    @JsonProperty("changed_time_at")
    private LocalDateTime changedTimeAt;

    @JsonProperty("real_mode")
    private String realMode;

    @JsonProperty("real_program_id")
    private Long realProgramId;

    @JsonProperty("real_keep_phase_id")
    private Long realKeepPhaseId;

    @JsonProperty("program_created_at")
    private LocalDateTime programCreatedAt;

    @JsonProperty("program_updated_at")
    private LocalDateTime programUpdatedAt;

    @JsonProperty("t_cycle")
    private Long tCycle;

    @JsonProperty("program_type")
    private String programType;

    private List<Phase> phases;

    @JsonProperty("start_phase_id")
    private Long startPhaseId;
}
