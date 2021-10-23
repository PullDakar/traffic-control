package ru.jat.trafficcontrol.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ProgramCandidate {
    private final long roadControllerId;
    private final long[] phases = new long[3];
    private int weight = 1;
}
