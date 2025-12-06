package com.example.teacherservice.repository;

import com.example.teacherservice.entity.Schedule;
import com.example.teacherservice.entity.enumDay.Days;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {

   Optional<Schedule> findByDay(Days days);




}
