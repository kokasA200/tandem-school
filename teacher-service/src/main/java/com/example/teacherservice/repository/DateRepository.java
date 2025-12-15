package com.example.teacherservice.repository;

import com.example.teacherservice.entity.Date;
import com.example.teacherservice.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DateRepository extends JpaRepository<Date, Long> {

    Optional<Date> findByDay(int day);

    List<Subject> getSubjectsByDate(int day);
}
