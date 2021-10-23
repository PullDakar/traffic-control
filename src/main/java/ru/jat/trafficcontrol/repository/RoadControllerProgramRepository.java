package ru.jat.trafficcontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.jat.trafficcontrol.model.RoadControllerProgramEntity;

import java.util.List;

public interface RoadControllerProgramRepository extends JpaRepository<RoadControllerProgramEntity, Long> {
    List<RoadControllerProgramEntity> findAllByRoadControllerId(Long roadControllerId);
}
