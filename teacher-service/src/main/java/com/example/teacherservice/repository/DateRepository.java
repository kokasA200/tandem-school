package com.example.teacherservice.repository;

import com.example.teacherservice.entity.Date;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DateRepository extends JpaRepository<Date, Long> {

    Date findByDay(int day);
}
