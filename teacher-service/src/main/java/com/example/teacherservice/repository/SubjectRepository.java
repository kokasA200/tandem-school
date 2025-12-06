package com.example.teacherservice.repository;

import com.example.teacherservice.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject,Long> {
    Subject findByName(String name);
}
