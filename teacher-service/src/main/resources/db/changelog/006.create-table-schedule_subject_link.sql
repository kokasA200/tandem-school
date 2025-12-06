--liquibase formatted sql
--changeset Sergey:6

CREATE TABLE schedule_subject_link
(
    schedule_id BIGINT NOT NULL,
    subject_id  BIGINT NOT NULL,
    PRIMARY KEY (schedule_id, subject_id),
    CONSTRAINT fk_link_schedule FOREIGN KEY (schedule_id) REFERENCES schedules (id),
    CONSTRAINT fk_link_subject FOREIGN KEY (subject_id) REFERENCES subjects (id)
);

