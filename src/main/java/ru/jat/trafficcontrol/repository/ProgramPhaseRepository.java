package ru.jat.trafficcontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.jat.trafficcontrol.model.ProgramPhaseEntity;
import ru.jat.trafficcontrol.model.ProgramPhaseId;

public interface ProgramPhaseRepository extends JpaRepository<ProgramPhaseEntity, ProgramPhaseId> {
}
