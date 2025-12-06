package com.example.teacherservice.service;

import com.example.teacherservice.dto.*;
import com.example.teacherservice.entity.*;
import com.example.teacherservice.entity.enumDay.Days;
import com.example.teacherservice.exception.ScheduleNotFoundException;
import com.example.teacherservice.exception.StudentNotFoundException;
import com.example.teacherservice.repository.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GradeService {


    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final ScheduleRepository scheduleRepository;
    private final DateRepository dateRepository;


    public void giveGrade(String name, String surname,
                          String subjectName, String weekDay, int day, int MyGrade){

        Student student = studentRepository.findByNameAndSurname(name, surname)
                .orElseThrow(()-> new StudentNotFoundException("Student not found"));

        Subject subject = subjectRepository.findByName(subjectName);

        Optional<Schedule> schedule = scheduleRepository.findByDay(Days.valueOf(weekDay));

        Date date = dateRepository.findByDay(day);

        Grade grade = new Grade();
        grade.setStudent(student);
        grade.setSubject(subject);
        grade.setSchedule(schedule.get());
        grade.setDay(date);
        grade.setGrade(MyGrade);

        gradeRepository.save(grade);

    }



    public void updateGrade(Long gradeId, int gradeValue) {
        Optional<Grade> grade = gradeRepository.findById(gradeId);
        grade.get().setGrade(gradeValue);
    }




}



