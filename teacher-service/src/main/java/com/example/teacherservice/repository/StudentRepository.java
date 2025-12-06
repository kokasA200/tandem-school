package com.example.teacherservice.repository;

import com.example.teacherservice.entity.Student;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {


    Optional<Student> findByNameAndSurname(String studentName, String studentSurname);


//    @EntityGraph(attributePaths = {"group"})
    List<Student> findStudentByGroupNameAndGroupCourse(String groupName, int course);



}
