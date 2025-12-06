package com.example.teacherservice.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GroupDto {

    @NotBlank(message = "groupDto name shouldn't be empty")
    String name;

    @Min(value = 1)
    @Max(value = 5)
    @NotNull(message = "course shouldn't be null")
    int course;


}
