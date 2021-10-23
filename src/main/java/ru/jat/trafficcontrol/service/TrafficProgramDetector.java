package ru.jat.trafficcontrol.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import ru.jat.trafficcontrol.model.MonitoringEntity;
import ru.jat.trafficcontrol.model.ProgramBundle;
import ru.jat.trafficcontrol.repository.ProgramPhaseRepository;
import ru.jat.trafficcontrol.repository.RoadControllerProgramRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrafficProgramDetector {
    private final RoadControllerProgramRepository roadControllerProgramRepository;
    private final ProgramPhaseRepository programPhaseRepository;

    private final List<ProgramBundle> bundles = new ArrayList<>();
    private final List<ProgramBundle> programs = new ArrayList<>();

    private final static int POSSIBLE_PHASE_DIFF = 3 * 3;

    @EventListener
    public void onPhaseChange(MonitoringEntity monitoringEntity) {
        final var roadControllerId = monitoringEntity.getRoadControllerId();
        final var phaseId = monitoringEntity.getPhaseId();
        final var phaseDuration = monitoringEntity.getPhaseDuration();

        if (bundles.stream().noneMatch(bundle -> bundle.getRoadControllerId() == roadControllerId)) {
            final var programBundle = new ProgramBundle(roadControllerId);
            programBundle.getPhases()[(int) phaseId - 1] = phaseDuration;
            bundles.add(programBundle);
        } else {
            bundles.stream()
                    .filter(bundle -> bundle.getRoadControllerId() == roadControllerId)
                    .forEach(bundle -> {
                        long[] phases = bundle.getPhases();
                        if (phases[(int) phaseId - 1] == 0) {
                            phases[(int) phaseId - 1] = phaseDuration;
                        }

                        if (phases[0] * phases[1] * phases[2] != 0) {
                            if (programs.isEmpty())
                                programs.add(bundle);
                            else {
                                boolean programExists = false;
                                for (ProgramBundle program : programs) {
                                    if (program.getRoadControllerId() == bundle.getRoadControllerId()) {
                                        programExists = true;
                                        long[] programPhases = program.getPhases();
                                        long[] bundlePhases = bundle.getPhases();

                                        if (Math.pow(programPhases[0] - bundlePhases[0], 2) < POSSIBLE_PHASE_DIFF &&
                                                Math.pow(programPhases[1] - bundlePhases[1], 2) < POSSIBLE_PHASE_DIFF &&
                                                Math.pow(programPhases[2] - bundlePhases[2], 2) < POSSIBLE_PHASE_DIFF) {
                                            program.setCounter(program.getCounter() + 1);
                                        } else {
                                            programs.add(bundle);
                                        }
                                    }
                                }

                                if (!programExists)
                                    programs.add(bundle);
                            }
                        }
                    });
        }
    }
}
