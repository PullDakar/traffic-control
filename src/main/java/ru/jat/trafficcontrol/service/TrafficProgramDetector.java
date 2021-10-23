package ru.jat.trafficcontrol.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import ru.jat.trafficcontrol.model.MonitoringEntity;
import ru.jat.trafficcontrol.model.ProgramCandidate;
import ru.jat.trafficcontrol.model.RoadControllerProgramEntity;
import ru.jat.trafficcontrol.repository.RoadControllerProgramRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, реализующий логику выявления программ, запущенных на контроллере
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TrafficProgramDetector {
    /**
     * Абстракция для взаимодействия с БД с таблицей {@link RoadControllerProgramEntity}
     */
    private final RoadControllerProgramRepository roadControllerProgramRepository;
    /**
     * Буфер для наполнения программ-кандидатов
     */
    private final List<ProgramCandidate> buffer = new ArrayList<>();

    /**
     * Допустимое отклонение
     */
    private final static int POSSIBLE_PHASE_DIFF = 3 * 3;
    /**
     * Аномальное отклонение
     */
    private final static int ANOMALY_THRESHOLD = 30 * 30;

    /**
     * Слушатель событий изменений фазы
     *
     * Реализует основную логику отсеивания программ от программ-кандидатов и программ-аномалий
     *
     * @param monitoringEntity - сущность, описывающая фазу мониторинга
     */
    @EventListener
    public void onPhaseChange(MonitoringEntity monitoringEntity) {
        final var roadControllerId = monitoringEntity.getRoadControllerId();
        final var phaseId = monitoringEntity.getPhaseId();
        final var phaseDuration = monitoringEntity.getPhaseDuration();

        if (buffer.stream().noneMatch(bundle -> bundle.getRoadControllerId() == roadControllerId)) {
            final var programBundle = new ProgramCandidate(roadControllerId);
            programBundle.getPhases()[(int) phaseId - 1] = phaseDuration;
            buffer.add(programBundle);
        } else {
            for (ProgramCandidate candidate : new ArrayList<>(buffer)) {
                if (candidate.getRoadControllerId() == roadControllerId) {
                    long[] phases = candidate.getPhases();
                    if (phases[(int) phaseId - 1] == 0) {
                        phases[(int) phaseId - 1] = phaseDuration;
                    }

                    if (phases[0] * phases[1] * phases[2] != 0) {
                        List<RoadControllerProgramEntity> programs
                                = roadControllerProgramRepository.findAllByRoadControllerId(roadControllerId);
                        if (programs.isEmpty()) {
                            roadControllerProgramRepository.save(RoadControllerProgramEntity.builder()
                                    .roadControllerId(roadControllerId)
                                    .programName("program#" + monitoringEntity.getProgramId())
                                    .firstPhaseDuration(phases[0])
                                    .secondPhaseDuration(phases[1])
                                    .thirdPhaseDuration(phases[2])
                                    .weight(candidate.getWeight()).build());

                            buffer.remove(candidate);
                        } else {
                            boolean programExists = false;
                            for (RoadControllerProgramEntity program : programs) {
                                if (program.getRoadControllerId() == candidate.getRoadControllerId()) {

                                    long[] candidatePhases = candidate.getPhases();

                                    if (Math.pow(program.getFirstPhaseDuration() - candidatePhases[0], 2) <= POSSIBLE_PHASE_DIFF &&
                                            Math.pow(program.getSecondPhaseDuration() - candidatePhases[1], 2) <= POSSIBLE_PHASE_DIFF &&
                                            Math.pow(program.getThirdPhaseDuration() - candidatePhases[2], 2) <= POSSIBLE_PHASE_DIFF) {
                                        program.setWeight(program.getWeight() + 1);
                                        roadControllerProgramRepository.save(program);
                                        programExists = true;
                                        buffer.remove(candidate);
                                        break;
                                    } else if (Math.pow(program.getFirstPhaseDuration() - candidatePhases[0], 2) >= ANOMALY_THRESHOLD ||
                                            Math.pow(program.getSecondPhaseDuration() - candidatePhases[1], 2) >= ANOMALY_THRESHOLD ||
                                            Math.pow(program.getThirdPhaseDuration() - candidatePhases[2], 2) >= ANOMALY_THRESHOLD) {
                                        program.setAnomaly(true);
                                        program.setWeight(0);
                                        roadControllerProgramRepository.save(program);
                                    }
                                }
                            }

                            if (!programExists) {
                                roadControllerProgramRepository.save(RoadControllerProgramEntity.builder()
                                        .roadControllerId(roadControllerId)
                                        .programName("program#" + monitoringEntity.getProgramId())
                                        .firstPhaseDuration(phases[0])
                                        .secondPhaseDuration(phases[1])
                                        .thirdPhaseDuration(phases[2])
                                        .weight(candidate.getWeight()).build());

                                buffer.remove(candidate);
                            }
                        }
                    }
                }
            }
        }
    }
}
