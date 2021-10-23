package ru.jat.trafficcontrol.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProgramPhaseId implements Serializable {
    private long roadControllerId;
    private long programId;
    private long phaseId;
}
