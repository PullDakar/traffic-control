CREATE TABLE monitoring
(
    id                 INT AUTO_INCREMENT,
    road_controller_id VARCHAR NOT NULL,
    switch_moment      DATE DEFAULT current_timestamp(),
    phase_duration     BIGINT  NOT NULL
);