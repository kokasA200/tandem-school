--liquibase formatted sql
--changeset Sergey:2

CREATE TABLE groups
(
    id     BIGSERIAL PRIMARY KEY,
    course INTEGER,
    name   VARCHAR(255)
);

