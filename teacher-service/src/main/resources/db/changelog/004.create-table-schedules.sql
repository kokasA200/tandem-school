--liquibase formatted sql
--changeset Sergey:4

CREATE TABLE schedules
(
    id  BIGSERIAL PRIMARY KEY,
    day VARCHAR(255)
);

