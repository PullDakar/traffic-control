package ru.jat.trafficcontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.jat.trafficcontrol.model.RoadControllerProgramEntity;
import ru.jat.trafficcontrol.model.RoadControllerProgramId;

public interface RoadControllerProgramRepository extends JpaRepository<RoadControllerProgramEntity, RoadControllerProgramId> {
}
