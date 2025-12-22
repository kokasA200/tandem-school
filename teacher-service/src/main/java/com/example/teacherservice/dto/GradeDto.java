package com.example.teacherservice.dto;


import com.example.teacherservice.entity.Student;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GradeDto {

    @Schema(description = "stident grade", examples = "4")
    @Size(min = 2, max = 5)
    int Grade;


    @Valid
    @Schema(description = "information about student")
    Student student;


}
