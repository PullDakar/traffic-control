package ru.jat.trafficcontrol.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ProgramBundle {
    private final long roadControllerId;
    private final long[] phases = new long[3];
    private int counter = 0;
}
