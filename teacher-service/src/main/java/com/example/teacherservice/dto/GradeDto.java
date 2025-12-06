package com.example.teacherservice.dto;


import com.example.teacherservice.entity.Student;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GradeDto {


    @Size(min = 2, max = 5)
    int Grade;


    @Valid
    Student student;


}
