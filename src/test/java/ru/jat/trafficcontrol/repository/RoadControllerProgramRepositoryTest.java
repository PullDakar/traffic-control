package ru.jat.trafficcontrol.repository;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Disabled
@SpringBootTest
class RoadControllerProgramRepositoryTest {

    @Autowired
    RoadControllerProgramRepository roadControllerProgramRepository;

    @Test
    void findTop1ByRoadControllerIdAndWeightGreaterThanEqualOrderByUpdatedDesc() {
        roadControllerProgramRepository.findTop1ByRoadControllerIdAndWeightGreaterThanEqualOrderByUpdatedDesc(33051L,3);
    }
}