--liquibase formatted sql
--changeset Sergey:1

CREATE TABLE grades
(
    id          BIGSERIAL PRIMARY KEY,
    grade       INTEGER NOT NULL,
    student_id  BIGINT  NOT NULL,
    subject_id  BIGINT  NOT NULL,
    schedule_id BIGINT  NOT NULL,
    date_id     BIGINT  NOT NULL
    CONSTRAINT fk_grade_student FOREIGN KEY (student_id) REFERENCES students (id),
    CONSTRAINT fk_grade_subject FOREIGN KEY (subject_id) REFERENCES subjects (id),
    CONSTRAINT fk_grade_schedule FOREIGN KEY (schedule_id) REFERENCES schedules (id),
    CONSTRAINT fk_grade_date FOREIGN KEY (date_id) REFERENCES date (id)
);


