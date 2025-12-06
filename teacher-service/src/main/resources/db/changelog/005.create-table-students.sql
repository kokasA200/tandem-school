--liquibase formatted sql
--changeset Sergey:5

CREATE TABLE students
(
    id       BIGSERIAL PRIMARY KEY,
    name     VARCHAR(255),
    surname  VARCHAR(255),
    group_id BIGINT,
    CONSTRAINT fk_student_group FOREIGN KEY (group_id) REFERENCES groups (id)
);

