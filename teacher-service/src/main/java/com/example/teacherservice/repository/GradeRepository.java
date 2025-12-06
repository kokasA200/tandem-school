package com.example.teacherservice.repository;

import com.example.teacherservice.entity.Grade;
import com.example.teacherservice.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GradeRepository extends JpaRepository<Grade,Long> {

}
