package ru.jat.trafficcontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.jat.trafficcontrol.model.RoadControllerProgramEntity;

import java.util.List;

/**
 * Абстракция для взаимодействия с БД с таблицей {@link RoadControllerProgramEntity}
 */
@Repository
public interface RoadControllerProgramRepository extends JpaRepository<RoadControllerProgramEntity, Long> {
    /**
     * Поиск всех программ по идентификатору контроллера
     *
     * @param roadControllerId - идентификатор контроллера
     *
     * @return набор программ, установленных на контроллере
     */
    List<RoadControllerProgramEntity> findAllByRoadControllerId(Long roadControllerId);

    /**
     * Поиск программы, запущенной на контроллере
     *
     * @return программа, запущенная на контроллере
     */
    @Query("select rcpe from RoadControllerProgramEntity rcpe where rcpe.weight >= 3 order by rcpe.updated desc")
    RoadControllerProgramEntity findCurrentProgram();
}
