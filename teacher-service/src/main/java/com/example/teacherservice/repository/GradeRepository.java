package com.example.teacherservice.repository;

import com.example.teacherservice.entity.Date;
import com.example.teacherservice.entity.Grade;
import com.example.teacherservice.entity.Student;
import com.example.teacherservice.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GradeRepository extends JpaRepository<Grade,Long> {

    int getGradeBy(Student student, Subject subject, Date date);

//    List<Grade> findByStudentAndDay(Student student, int day);





    @Query("SELECT g FROM Grade g JOIN FETCH g.subject " +
            "WHERE g.student = :student AND g.day = :date")
    List<Grade> findByStudentAndDateWithSubject(@Param("student") Student student, @Param("day") Date day);





    @Query("SELECT g FROM Grade g JOIN FETCH g.subject JOIN FETCH g.student WHERE g.day = :date")
    List<Grade> findByDateWithSubjectAndStudent(@Param("date") Date date);
}
