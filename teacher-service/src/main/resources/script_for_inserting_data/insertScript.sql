-- 1. Заполнение таблицы 'date' (предполагаем, что это даты занятий или оценок)
INSERT INTO date (day) VALUES
(1), (2),  (3), (4), (5),
(6), (7),  (8), (9), (10),
(11),(12), (13),(14),(15),
(16),(17), (18),(19),(20),
(21),(22), (23),(24),(25),
(26),(27), (28),(29),(30);

-- 2. Заполнение таблицы 'groups'
INSERT INTO groups (course, name)
VALUES (1, 'ЧЛР'),
       (2, 'РЛ');


-- 3. Заполнение таблицы 'subjects'
INSERT INTO subjects (subject_name)
VALUES ('Математика'),
       ('Физика'),
       ('Информатика'),
       ('История'),
       ('Литература'),
       ('Английский язык');

-- 4. Заполнение таблицы 'schedules' (расписания - просто дни недели)
INSERT INTO schedules (day)
VALUES ('MONDAY'),
       ('TUESDAY'),
       ('WEDNESDAY'),
       ('THURSDAY'),
       ('FRIDAY'),
       ('SATURDAY'),
       ('SUNDAY');

-- -- 5. Заполнение таблицы 'students' (используем group_id из таблицы groups)
INSERT INTO students (name, surname, group_id)
VALUES ('Иван', 'Иванов', (SELECT id FROM groups WHERE name = 'ЧЛР')),
       ('Мария', 'Петрова', (SELECT id FROM groups WHERE name = 'ЧЛР')),
       ('Алексей', 'Сидоров', (SELECT id FROM groups WHERE name = 'ЧЛР')),
       ('Елена', 'Козлова', (SELECT id FROM groups WHERE name = 'ЧЛР')),
       ('Дмитрий', 'Смирнов', (SELECT id FROM groups WHERE name = 'ЧЛР')),
       ('Анна', 'Кузнецова', (SELECT id FROM groups WHERE name = 'ЧЛР')),
       ('Сергей', 'Морозов', (SELECT id FROM groups WHERE name = 'РЛ')),
       ('Ольга', 'Волкова', (SELECT id FROM groups WHERE name = 'РЛ')),
       ('Петр', 'Новиков', (SELECT id FROM groups WHERE name = 'РЛ')),
       ('Наталья', 'Зайцева', (SELECT id FROM groups WHERE name = 'РЛ'));

-- 6. Заполнение таблицы 'schedule_subject_link'
INSERT INTO schedule_subject_link (schedule_id, subject_id)
VALUES ((SELECT id FROM schedules WHERE day = 'MONDAY'),
       (SELECT id FROM subjects WHERE subject_name = 'Математика')),

       ((SELECT id FROM schedules WHERE day = 'MONDAY'),
       (SELECT id FROM subjects WHERE subject_name = 'Информатика')),

       ((SELECT id FROM schedules WHERE day = 'TUESDAY'),
       (SELECT id FROM subjects WHERE subject_name = 'Физика')),

       ((SELECT id FROM schedules WHERE day = 'TUESDAY'),
       (SELECT id FROM subjects WHERE subject_name = 'Английский язык')),

       ((SELECT id FROM schedules WHERE day = 'WEDNESDAY'),
       (SELECT id FROM subjects WHERE subject_name = 'История')),

       ((SELECT id FROM schedules WHERE day = 'WEDNESDAY'),
       (SELECT id FROM subjects WHERE subject_name = 'Математика')),

       ((SELECT id FROM schedules WHERE day = 'THURSDAY'),
        (SELECT id FROM subjects WHERE subject_name = 'Литература')),

       ((SELECT id FROM schedules WHERE day = 'THURSDAY'),
       (SELECT id FROM subjects WHERE subject_name = 'Информатика')),

       ((SELECT id FROM schedules WHERE day = 'FRIDAY'),
       (SELECT id FROM subjects WHERE subject_name = 'Физика')),

       ((SELECT id FROM schedules WHERE day = 'FRIDAY'),
       (SELECT id FROM subjects WHERE subject_name = 'Английский язык'));



-- 7. Заполненение таблицы grades
   INSERT INTO grades (grade, student_id, subject_id, schedule_id, date_id)
   VALUES

-- Иван Иванов
      (5, (SELECT id FROM students WHERE name = 'Иван' AND surname = 'Иванов'),
      (SELECT id FROM subjects WHERE subject_name = 'Математика'),
      (SELECT id FROM schedules WHERE day = 'MONDAY'),
      (SELECT id FROM date WHERE day = 1)),


      (4, (SELECT id FROM students WHERE name = 'Иван' AND surname = 'Иванов'),
      (SELECT id FROM subjects WHERE subject_name = 'Информатика'),
      (SELECT id FROM schedules WHERE day = 'MONDAY'),
      (SELECT id FROM date WHERE day = 2)),

      (5, (SELECT id FROM students WHERE name = 'Иван' AND surname = 'Иванов'),
      (SELECT id FROM subjects WHERE subject_name = 'Математика'),
      (SELECT id FROM schedules WHERE day = 'WEDNESDAY'),
      (SELECT id FROM date WHERE day = 8)),

-- Мария Петрова
      (4, (SELECT id FROM students WHERE name = 'Мария' AND surname = 'Петрова'),
      (SELECT id FROM subjects WHERE subject_name = 'Математика'),
      (SELECT id FROM schedules WHERE day = 'MONDAY'),
      (SELECT id FROM date WHERE day = 1)),

      (5, (SELECT id FROM students WHERE name = 'Мария' AND surname = 'Петрова'),
      (SELECT id FROM subjects WHERE subject_name = 'Информатика'),
      (SELECT id FROM schedules WHERE day = 'MONDAY'),
      (SELECT id FROM date WHERE day = 3)),

-- Алексей Сидоров (Группа А2)
      (3, (SELECT id FROM students WHERE name = 'Алексей' AND surname = 'Сидоров'),
      (SELECT id FROM subjects WHERE subject_name = 'Физика'),
      (SELECT id FROM schedules WHERE day = 'TUESDAY'),
      (SELECT id FROM date WHERE day = 4)),

      (4, (SELECT id FROM students WHERE name = 'Алексей' AND surname = 'Сидоров'),
      (SELECT id FROM subjects WHERE subject_name = 'Английский язык'),
      (SELECT id FROM schedules WHERE day = 'TUESDAY'),
      (SELECT id FROM date WHERE day = 5)),

-- Елена Козлова (Группа А2)
      (5, (SELECT id FROM students WHERE name = 'Елена' AND surname = 'Козлова'),
      (SELECT id FROM subjects WHERE subject_name = 'Физика'),
      (SELECT id FROM schedules WHERE day = 'TUESDAY'),
      (SELECT id FROM date WHERE day = 4)),

      (4, (SELECT id FROM students WHERE name = 'Елена' AND surname = 'Козлова'),
      (SELECT id FROM subjects WHERE subject_name = 'Английский язык'),
      (SELECT id FROM schedules WHERE day = 'TUESDAY'),
      (SELECT id FROM date WHERE day = 5)),

-- Дмитрий Смирнов (Группа Б1)
      (4, (SELECT id FROM students WHERE name = 'Дмитрий' AND surname = 'Смирнов'),
      (SELECT id FROM subjects WHERE subject_name = 'История'),
      (SELECT id FROM schedules WHERE day = 'WEDNESDAY'),
      (SELECT id FROM date WHERE day = 6)),

      (5, (SELECT id FROM students WHERE name = 'Дмитрий' AND surname = 'Смирнов'),
      (SELECT id FROM subjects WHERE subject_name = 'Математика'),
      (SELECT id FROM schedules WHERE day = 'WEDNESDAY'),
      (SELECT id FROM date WHERE day = 7)),

-- Анна Кузнецова (Группа Б1)
      (5, (SELECT id FROM students WHERE name = 'Анна' AND surname = 'Кузнецова'),
      (SELECT id FROM subjects WHERE subject_name = 'История'),
      (SELECT id FROM schedules WHERE day = 'WEDNESDAY'),
      (SELECT id FROM date WHERE day = 6)),

      (4, (SELECT id FROM students WHERE name = 'Анна' AND surname = 'Кузнецова'),
      (SELECT id FROM subjects WHERE subject_name = 'Математика'),
      (SELECT id FROM schedules WHERE day = 'WEDNESDAY'),
      (SELECT id FROM date WHERE day = 7)),

-- Сергей Морозов (Группа Б2)
      (3, (SELECT id FROM students WHERE name = 'Сергей' AND surname = 'Морозов'),
      (SELECT id FROM subjects WHERE subject_name = 'Литература'),
      (SELECT id FROM schedules WHERE day = 'THURSDAY'),
      (SELECT id FROM date WHERE day = 9)),

      (4, (SELECT id FROM students WHERE name = 'Сергей' AND surname = 'Морозов'),
      (SELECT id FROM subjects WHERE subject_name = 'Информатика'),
      (SELECT id FROM schedules WHERE day = 'THURSDAY'),
      (SELECT id FROM date WHERE day = 10)),

-- Ольга Волкова (Группа Б2)
      (4, (SELECT id FROM students WHERE name = 'Ольга' AND surname = 'Волкова'),
      (SELECT id FROM subjects WHERE subject_name = 'Литература'),
      (SELECT id FROM schedules WHERE day = 'THURSDAY'),
      (SELECT id FROM date WHERE day = 9)),

      (5, (SELECT id FROM students WHERE name = 'Ольга' AND surname = 'Волкова'),
      (SELECT id FROM subjects WHERE subject_name = 'Информатика'),
      (SELECT id FROM schedules WHERE day = 'THURSDAY'),
      (SELECT id FROM date WHERE day = 10));

