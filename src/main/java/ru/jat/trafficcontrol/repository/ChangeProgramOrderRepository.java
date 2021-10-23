package ru.jat.trafficcontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.jat.trafficcontrol.model.ChangeProgramOrder;

import java.util.Optional;

@Repository
public interface ChangeProgramOrderRepository extends JpaRepository<ChangeProgramOrder, Integer> {

    Optional<ChangeProgramOrder> findTop1ByRoadControllerIdOrderByChangeTime(Long roadControllerId);
}
