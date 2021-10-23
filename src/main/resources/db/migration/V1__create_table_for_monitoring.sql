CREATE TABLE monitoring
(
    id                 BIGINT AUTO_INCREMENT,
    road_controller_id BIGINT NOT NULL,
    phase_id           BIGINT NOT NULL,
    program_id         BIGINT NOT NULL,
    switch_moment      TIMESTAMP DEFAULT current_timestamp(),
    phase_duration     BIGINT  NOT NULL
);