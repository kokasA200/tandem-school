package com.example.teacherservice.dto;

import com.example.teacherservice.entity.Subject;
import lombok.Data;

@Data
public class SubjectGradeDto {
    private Subject subject;
    private int gradeValue;


    public SubjectGradeDto(String name, int grade) {
    }
}