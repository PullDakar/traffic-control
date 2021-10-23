package ru.jat.trafficcontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.jat.trafficcontrol.model.RoadControllerProgramEntity;

import java.util.List;
import java.util.Optional;

public interface RoadControllerProgramRepository extends JpaRepository<RoadControllerProgramEntity, Long> {
    List<RoadControllerProgramEntity> findAllByRoadControllerId(Long roadControllerId);

    @Query("select rcpe from RoadControllerProgramEntity rcpe where rcpe.weight >= 3 and rcpe.roadControllerId = ?1 order by rcpe.updated desc")
    Optional<RoadControllerProgramEntity> findCurrentProgram(Long roadControllerId);
}
