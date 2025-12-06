package com.example.teacherservice.controller;


import com.example.teacherservice.dto.GroupDto;
import com.example.teacherservice.dto.StudentDto;
import com.example.teacherservice.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/studentApi")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;


    @PostMapping("/addStudent")
    public ResponseEntity<String> addStudent(@Validated @RequestBody StudentDto studentDto) {
        studentService.addStudent(studentDto);
        String addedStudent = "Student added successfully!";
        return ResponseEntity.ok().body(addedStudent);
    }

    @Validated
    @GetMapping("/getStudent")
    public ResponseEntity<StudentDto> getStudentByName(@RequestParam String name, @RequestParam String surname) {
        StudentDto studentDto = studentService.getStudentByName(name, surname);
        return ResponseEntity.ok().body(studentDto);
    }


    @GetMapping("/getList")
    @Validated
    public ResponseEntity<List<StudentDto>> getAllStudents(@RequestParam(required = false) String name,
                                                           @RequestParam(required = false) int course
                                                           ) {

        List<StudentDto> studentsDtoList = studentService.getAllStudentsByGroup(name, course);
        return ResponseEntity.ok().body(studentsDtoList);
    }


    @Validated
    @DeleteMapping("/deleteStudent/{name}/{surname}")
    public ResponseEntity<String> deleteStudent(@PathVariable String name, @PathVariable String surname){
        studentService.deleteStudentByName(name, surname);
        String message = "Delete student successfully!";
        return ResponseEntity.ok().body(message);
    }

}





