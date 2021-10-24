package ru.jat.trafficcontrol.repository;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import ru.jat.trafficcontrol.model.ChangeProgramOrder;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Disabled
@SpringBootTest
class ChangeProgramOrderRepositoryTest {

    @Autowired
    ChangeProgramOrderRepository changeProgramOrderRepository;

    @Rollback
    @Test
    void findTop1ByRoadControllerIdOrderByChangeTime() {
        var a = ChangeProgramOrder.builder()
                .changeTime(Timestamp.valueOf(LocalDateTime.now()))
                .newProgramId(1)
                .roadControllerId(9999).build();
        changeProgramOrderRepository.save(a);

        var b = ChangeProgramOrder.builder()
                .changeTime(Timestamp.valueOf(LocalDateTime.now()))
                .newProgramId(2)
                .roadControllerId(9999).build();
        changeProgramOrderRepository.save(b);

        assertTrue(changeProgramOrderRepository.findTop1ByRoadControllerIdOrderByChangeTime(9999L).isPresent());
        assertTrue(changeProgramOrderRepository.findTop1ByRoadControllerIdOrderByChangeTime(9999L).get().getNewProgramId() == 1L);
    }
}