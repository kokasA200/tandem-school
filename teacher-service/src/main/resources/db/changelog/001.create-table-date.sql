--liquibase formatted sql
--changeset Sergey:1

CREATE TABLE date (
    id BIGSERIAL PRIMARY KEY,
    day INTEGER
);

