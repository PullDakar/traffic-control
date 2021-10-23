package ru.jat.trafficcontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.jat.trafficcontrol.model.MonitoringEntity;

/**
 * Абстракция для взаимодействия с БД с таблицей {@link MonitoringEntity}
 */
@Repository
public interface MonitoringRepository extends JpaRepository<MonitoringEntity, Long> {
}
