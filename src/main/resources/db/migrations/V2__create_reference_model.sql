CREATE TABLE road_controller_program
(
    id                 BIGINT  NOT NULL,
    road_controller_id BIGINT  NOT NULL,
    program_name       VARCHAR,
    program_type       VARCHAR,
    phase_count        BIGINT  NOT NULL,

    PRIMARY KEY (id, road_controller_id)
);

CREATE TABLE program_phase
(
    road_controller_id BIGINT  NOT NULL,
    program_id         BIGINT  NOT NULL,
    phase_id           BIGINT  NOT NULL,
    phase_duration     BIGINT  NOT NULL,

    PRIMARY KEY (road_controller_id, program_id, phase_id)
);

INSERT INTO road_controller_program (id, road_controller_id, program_name, program_type, phase_count) values ( 1, 33051, 'FIRST', 'LOCAL', 3);
INSERT INTO road_controller_program (id, road_controller_id, program_name, program_type, phase_count) values ( 2, 33051, 'SECOND', 'WORK', 3);
INSERT INTO road_controller_program (id, road_controller_id, program_name, program_type, phase_count) values ( 1, 33052, 'FIRST', 'LOCAL', 3);
INSERT INTO road_controller_program (id, road_controller_id, program_name, program_type, phase_count) values ( 1, 33053, 'FIRST', 'LOCAL', 3);
INSERT INTO road_controller_program (id, road_controller_id, program_name, program_type, phase_count) values ( 1, 33054, 'FIRST', 'LOCAL', 3);
INSERT INTO road_controller_program (id, road_controller_id, program_name, program_type, phase_count) values ( 1, 33055, 'FIRST', 'LOCAL', 3);
INSERT INTO road_controller_program (id, road_controller_id, program_name, program_type, phase_count) values ( 1, 33056, 'FIRST', 'LOCAL', 3);
INSERT INTO road_controller_program (id, road_controller_id, program_name, program_type, phase_count) values ( 1, 33057, 'FIRST', 'LOCAL', 3);
INSERT INTO road_controller_program (id, road_controller_id, program_name, program_type, phase_count) values ( 1, 33058, 'FIRST', 'LOCAL', 3);
INSERT INTO road_controller_program (id, road_controller_id, program_name, program_type, phase_count) values ( 1, 33059, 'FIRST', 'LOCAL', 3);
INSERT INTO road_controller_program (id, road_controller_id, program_name, program_type, phase_count) values ( 1, 33060, 'FIRST', 'LOCAL', 3);

INSERT INTO program_phase (road_controller_id, program_id, phase_id, phase_duration) VALUES ( 33051, 1, 1, 25 );
INSERT INTO program_phase (road_controller_id, program_id, phase_id, phase_duration) VALUES ( 33051, 1, 2, 18 );
INSERT INTO program_phase (road_controller_id, program_id, phase_id, phase_duration) VALUES ( 33051, 1, 3, 30 );
INSERT INTO program_phase (road_controller_id, program_id, phase_id, phase_duration) VALUES ( 33051, 2, 3, 17 );
INSERT INTO program_phase (road_controller_id, program_id, phase_id, phase_duration) VALUES ( 33051, 2, 3, 18 );
INSERT INTO program_phase (road_controller_id, program_id, phase_id, phase_duration) VALUES ( 33051, 2, 3, 10 );
INSERT INTO program_phase (road_controller_id, program_id, phase_id, phase_duration) VALUES ( 33052, 1, 1, 15 );
INSERT INTO program_phase (road_controller_id, program_id, phase_id, phase_duration) VALUES ( 33052, 1, 2, 21 );
INSERT INTO program_phase (road_controller_id, program_id, phase_id, phase_duration) VALUES ( 33052, 1, 3, 25 );
INSERT INTO program_phase (road_controller_id, program_id, phase_id, phase_duration) VALUES ( 33053, 1, 1, 17 );
INSERT INTO program_phase (road_controller_id, program_id, phase_id, phase_duration) VALUES ( 33053, 1, 2, 21 );
INSERT INTO program_phase (road_controller_id, program_id, phase_id, phase_duration) VALUES ( 33053, 1, 3, 22 );
INSERT INTO program_phase (road_controller_id, program_id, phase_id, phase_duration) VALUES ( 33054, 1, 1, 18 );
INSERT INTO program_phase (road_controller_id, program_id, phase_id, phase_duration) VALUES ( 33054, 1, 2, 22 );
INSERT INTO program_phase (road_controller_id, program_id, phase_id, phase_duration) VALUES ( 33054, 1, 3, 17 );
INSERT INTO program_phase (road_controller_id, program_id, phase_id, phase_duration) VALUES ( 33055, 1, 1, 26 );
INSERT INTO program_phase (road_controller_id, program_id, phase_id, phase_duration) VALUES ( 33055, 1, 2, 30 );
INSERT INTO program_phase (road_controller_id, program_id, phase_id, phase_duration) VALUES ( 33055, 1, 3, 18 );
INSERT INTO program_phase (road_controller_id, program_id, phase_id, phase_duration) VALUES ( 33056, 1, 1, 22 );
INSERT INTO program_phase (road_controller_id, program_id, phase_id, phase_duration) VALUES ( 33056, 1, 2, 19 );
INSERT INTO program_phase (road_controller_id, program_id, phase_id, phase_duration) VALUES ( 33056, 1, 3, 15 );
INSERT INTO program_phase (road_controller_id, program_id, phase_id, phase_duration) VALUES ( 33057, 1, 3, 18 );
INSERT INTO program_phase (road_controller_id, program_id, phase_id, phase_duration) VALUES ( 33057, 1, 3, 17 );
INSERT INTO program_phase (road_controller_id, program_id, phase_id, phase_duration) VALUES ( 33057, 1, 3, 25 );
INSERT INTO program_phase (road_controller_id, program_id, phase_id, phase_duration) VALUES ( 33058, 1, 1, 27 );
INSERT INTO program_phase (road_controller_id, program_id, phase_id, phase_duration) VALUES ( 33058, 1, 2, 15 );
INSERT INTO program_phase (road_controller_id, program_id, phase_id, phase_duration) VALUES ( 33058, 1, 3, 21 );
INSERT INTO program_phase (road_controller_id, program_id, phase_id, phase_duration) VALUES ( 33059, 1, 1, 30 );
INSERT INTO program_phase (road_controller_id, program_id, phase_id, phase_duration) VALUES ( 33059, 1, 2, 29 );
INSERT INTO program_phase (road_controller_id, program_id, phase_id, phase_duration) VALUES ( 33059, 1, 3, 22 );
INSERT INTO program_phase (road_controller_id, program_id, phase_id, phase_duration) VALUES ( 33060, 1, 1, 25 );
INSERT INTO program_phase (road_controller_id, program_id, phase_id, phase_duration) VALUES ( 33060, 1, 2, 17 );
INSERT INTO program_phase (road_controller_id, program_id, phase_id, phase_duration) VALUES ( 33060, 1, 3, 22 );