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
public class RoadControllerProgramId implements Serializable {
    private long id;
    private long roadControllerId;
}
