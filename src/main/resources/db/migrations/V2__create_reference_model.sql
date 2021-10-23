CREATE TABLE road_controller_program
(
    id                    BIGINT AUTO_INCREMENT,
    road_controller_id    BIGINT NOT NULL,
    program_name          VARCHAR,
    program_type          VARCHAR,
    first_phase_duration  BIGINT NOT NULL,
    second_phase_duration BIGINT NOT NULL,
    third_phase_duration  BIGINT NOT NULL,
    weight                INT    NOT NULL,
    updated               TIMESTAMP DEFAULT current_timestamp(),
);