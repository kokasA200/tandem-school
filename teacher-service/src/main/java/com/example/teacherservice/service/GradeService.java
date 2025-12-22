package com.example.teacherservice.service;

import com.example.teacherservice.dto.SubjectGradeDto;
import com.example.teacherservice.entity.*;
import com.example.teacherservice.entity.enumDay.Days;
import com.example.teacherservice.exception.DateNotFoundException;
import com.example.teacherservice.exception.StudentNotFoundException;
import com.example.teacherservice.repository.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GradeService {


    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final ScheduleRepository scheduleRepository;
    private final DateRepository dateRepository;


    public void giveGrade(String name, String surname,
                          String subjectName, String weekDay, int day, int MyGrade) {

        Student student = studentRepository.findByNameAndSurname(name, surname)
                .orElseThrow(() -> new StudentNotFoundException("Student not found"));

        Subject subject = subjectRepository.findByName(subjectName);

        Optional<Schedule> schedule = scheduleRepository.findByDay(Days.valueOf(weekDay));

        Optional<Date> date = dateRepository.findByDay(day);

        Grade grade = new Grade();
        grade.setStudent(student);
        grade.setSubject(subject);
        grade.setSchedule(schedule.get());
        grade.setDay(date.get());
        grade.setGrade(MyGrade);

        gradeRepository.save(grade);

    }


    public void updateGrade(Long gradeId, int gradeValue) {
        Optional<Grade> grade = gradeRepository.findById(gradeId);
        grade.get().setGrade(gradeValue);
    }




//    public List<Integer> getGrades(String name, String surname, int day) {
//        Student student = studentRepository.findByNameAndSurname(name, surname)
//                .orElseThrow(() -> new StudentNotFoundException("Student not found"));
//
//        List<Grade> grades = gradeRepository.findByStudentAndDay(student, day);
//
//        return grades.stream()
//                .map(Grade::getGrade)
//                .collect(Collectors.toList());
//    }


    public List<SubjectGradeDto> getGradesWithSubjects(String name, String surname, int day) {
        Student student = studentRepository.findByNameAndSurname(name, surname)
                .orElseThrow(() -> new StudentNotFoundException("Student not found"));

        Date date = dateRepository.findByDay(day)
                .orElseThrow(() -> new DateNotFoundException("Date not found for day: " + day));

        List<Grade> grades = gradeRepository.findByStudentAndDateWithSubject(student, date);

        return grades.stream()
                .map(grade -> new SubjectGradeDto(grade.getSubject().getName(), grade.getGrade()))
                .collect(Collectors.toList());
    }


    public Map<String, List<SubjectGradeDto>> getAllStudentsGradesForDay(int day) {
        Date date = dateRepository.findByDay(day)
                .orElseThrow(() -> new DateNotFoundException("Date not found for day: " + day));

        List<Grade> grades = gradeRepository.findByDateWithSubjectAndStudent(date);

        return grades.stream()
                .collect(Collectors.groupingBy(
                        grade -> grade.getStudent().getName() + " " + grade.getStudent().getSurname(),
                        Collectors.mapping(
                                grade -> new SubjectGradeDto(grade.getSubject().getName(), grade.getGrade()),
                                Collectors.toList()
                        )
                ));
    }

}



