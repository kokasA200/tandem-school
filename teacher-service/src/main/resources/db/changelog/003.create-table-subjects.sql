--liquibase formatted sql
--changeset Sergey:3

CREATE TABLE subjects
(
    id           BIGSERIAL PRIMARY KEY,
    subject_name VARCHAR(255)
);

