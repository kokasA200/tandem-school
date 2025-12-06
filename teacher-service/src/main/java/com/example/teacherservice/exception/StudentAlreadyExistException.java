package com.example.teacherservice.exception;

import com.example.teacherservice.dto.StudentDto;
import com.example.teacherservice.entity.Student;
import lombok.Getter;

public class StudentAlreadyExistException extends RuntimeException {

    public StudentAlreadyExistException(String message) {
        super(message);
    }

}
