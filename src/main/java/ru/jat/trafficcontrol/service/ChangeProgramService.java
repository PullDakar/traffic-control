package ru.jat.trafficcontrol.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.jat.trafficcontrol.model.CustomPhaseProgramRequest;
import ru.jat.trafficcontrol.model.Phase;
import ru.jat.trafficcontrol.model.RoadControllerProgramEntity;
import ru.jat.trafficcontrol.repository.ChangeProgramOrderRepository;
import ru.jat.trafficcontrol.repository.RoadControllerProgramRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChangeProgramService {

    private final ChangeProgramOrderRepository changeProgramOrderRepository;
    private final RoadControllerProgramRepository roadControllerProgramRepository;
    private final Map<Long, CustomPhaseProgramRequest> customPhaseProgramRequestMap;
    private final TrafficLightService trafficLightService;


    @Scheduled(fixedRate = 1_000)
    public void changeProgramSchedule33051() {
        changeProgram(33051L);
    }

    @Scheduled(fixedRate = 1_000)
    public void changeProgramSchedule33052() {
        changeProgram(33052L);
    }

    @Scheduled(fixedRate = 1_000)
    public void changeProgramSchedule33053() {
        changeProgram(33053L);
    }

    @Scheduled(fixedRate = 1_000)
    public void changeProgramSchedule33054() {
        changeProgram(33054L);
    }

    @Scheduled(fixedRate = 1_000)
    public void changeProgramSchedule33055() {
        changeProgram(33055L);
    }

    @Scheduled(fixedRate = 1_000)
    public void changeProgramSchedule33056() {
        changeProgram(33056L);
    }

    @Scheduled(fixedRate = 1_000)
    public void changeProgramSchedule33057() {
        changeProgram(33057L);
    }

    @Scheduled(fixedRate = 1_000)
    public void changeProgramSchedule33058() {
        changeProgram(33058L);
    }

    @Scheduled(fixedRate = 1_000)
    public void changeProgramSchedule33059() {
        changeProgram(33059L);
    }

    @Scheduled(fixedRate = 1_000)
    public void changeProgramSchedule33060() {
        changeProgram(33060L);
    }

    @SneakyThrows
    private void changeProgram(Long id) {
        var changeProgramOrder = changeProgramOrderRepository.findTop1ByRoadControllerIdOrderByChangeTime(id).orElse(null);
        if (changeProgramOrder != null) {
            var currentTime = Timestamp.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
            if (changeProgramOrder.getChangeTime().after(currentTime) && changeProgramOrder.getChangeTime().before(Timestamp.valueOf(currentTime.toLocalDateTime().plusMinutes(5L)))) {
                var c = roadControllerProgramRepository.findTop1ByRoadControllerIdAndWeightGreaterThanEqualOrderByUpdatedDesc(id, 3).orElse(RoadControllerProgramEntity.builder()
                        .firstPhaseDuration(10)
                        .secondPhaseDuration(0)
                        .firstPhaseDuration(0)
                        .build());
                var maxPhaseTime = Map.of(1L, c.getFirstPhaseDuration(), 2L, c.getSecondPhaseDuration(), 3L, c.getThirdPhaseDuration()).entrySet().stream().max(Map.Entry.comparingByValue()).get();
                if (trafficLightService.getTrafficLightStatus(id).getCurrentPhaseId() != maxPhaseTime.getKey()) {
                    boolean maxPhaseFlag = true;
                    while (maxPhaseFlag) {
                        Thread.sleep(1000);
                        var status = trafficLightService.getTrafficLightStatus(id);
                        log.info(status.toString());
                        if (status.getCurrentPhaseId() == maxPhaseTime.getKey()) {
                            trafficLightService.holdPhase(id, maxPhaseTime.getKey()); //hold
                            var program = customPhaseProgramRequestMap.get(changeProgramOrder.getNewProgramId());
                            program.setStartPhaseId(maxPhaseTime.getKey());
                            program.setTimeStartSync(status.getTime() + maxPhaseTime.getValue() - 2L);
                            program.setTCycle(program.getPhases().stream().mapToLong(Phase::getTOsn).sum() + program.getPhases().stream().mapToLong(Phase::getTProm).sum());
                            var customPhaseProgramResponse = trafficLightService.changeProgram(id, program);
                            trafficLightService.unholdPhase(id); //unhold
                            log.info(customPhaseProgramResponse.toString());
                            if ("OK".equalsIgnoreCase(customPhaseProgramResponse.getStatus())) {
                                changeProgramOrderRepository.delete(changeProgramOrder);
                            }
                            maxPhaseFlag = false;
                        }
                    }
                }
                //1. Достать текущую программу и найти максимальную по времени фазу. Допустим 3 и время 20с.
                //2. Получить текущую фазу с ДК. Допустим 1, то ждем 3 -> и тогда есть 20с на переключение.
                //3. Захолдить фазу 3
                //4. Отправить программу с начальной фазой 3 и time_start_sync равным текущему времени + оставшемуся времени от максимальной фазы минус пару секунд.
                //5. Расхолдить фазу 3.
            } else {
                changeProgramOrderRepository.delete(changeProgramOrder);
            }
        }

    }
}
