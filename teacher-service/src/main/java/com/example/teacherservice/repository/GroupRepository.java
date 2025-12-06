package com.example.teacherservice.repository;

import com.example.teacherservice.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Integer> {
    List<Group> findByNameAndCourse(String name, int course);
}
