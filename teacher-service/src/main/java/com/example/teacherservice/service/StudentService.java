package com.example.teacherservice.service;

import com.example.teacherservice.dto.GroupDto;
import com.example.teacherservice.dto.StudentDto;
import com.example.teacherservice.entity.Grade;
import com.example.teacherservice.entity.Group;
import com.example.teacherservice.entity.Student;
import com.example.teacherservice.exception.GroupNotFoundException;
import com.example.teacherservice.exception.StudentAlreadyExistException;
import com.example.teacherservice.exception.StudentNotFoundException;
import com.example.teacherservice.repository.GroupRepository;
import com.example.teacherservice.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    @Transactional
    public void addStudent(StudentDto studentDto) {
        Optional<Student> existingStudentOptional = studentRepository.findByNameAndSurname(
                studentDto.getName(),
                studentDto.getSurname());

        if (existingStudentOptional.isPresent()) {
            throw new StudentAlreadyExistException(
                    "Student with name " + studentDto.getName() + " " + studentDto.getSurname() + " already exists.");
        } else {
            Student newStudent = new Student();
            newStudent.setName(studentDto.getName());
            newStudent.setSurname(studentDto.getSurname());

            Group group = new Group();
            group.setName(studentDto.getGroupDto().getName());
            group.setCourse(studentDto.getGroupDto().getCourse());
            newStudent.setGroup(group);

            studentRepository.save(newStudent);
        }
    }

    @Transactional(readOnly = true)
    public StudentDto getStudentByName(String name, String surname) {
        Optional<Student> studentOptional = studentRepository.findByNameAndSurname(name, surname);
        StudentDto studentDto = new StudentDto();

        if (studentOptional.isPresent()) {
            studentDto.setName(studentOptional.get().getName());
            studentDto.setSurname(studentOptional.get().getSurname());

            GroupDto groupDto = new GroupDto();
            groupDto.setName(studentOptional.get().getGroup().getName());
            groupDto.setCourse(studentOptional.get().getGroup().getCourse());

            studentDto.setGroupDto(groupDto);
        } else throw new StudentNotFoundException("Student not found with name: " + name + " and surname: " + surname);

        return studentDto;

    }


    @Transactional(readOnly = true)
        public List<StudentDto> getAllStudentsByGroup(String name, int course) {

        return studentRepository.findStudentByGroupNameAndGroupCourse(name, course).stream()
                .map(student -> {
                    GroupDto groupDto = new GroupDto();
                    groupDto.setName(student.getGroup().getName());
                    groupDto.setCourse(student.getGroup().getCourse());

                    StudentDto studentDto = new StudentDto();
                    studentDto.setName(student.getName());
                    studentDto.setSurname(student.getSurname());
                    studentDto.setGroupDto(groupDto);
                    return studentDto;

                })
                .collect(Collectors.toList());
      }




    public void deleteStudentByName(String name, String surname) {
        Optional<Student> student = Optional.ofNullable(studentRepository.findByNameAndSurname(name, surname)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with name: " + name + " and surname: " + surname)));

        }
    }



