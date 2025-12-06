package com.example.teacherservice.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDto {

    public StudentDto(String name, String surname, GroupDto groupDto) {
        this.name = name;
        this.surname = surname;
        this.groupDto = groupDto;
    }

    public StudentDto() {
    }

    @NotBlank(message = "name shouldn't be empty")
    String name;

    @NotBlank(message = "surname shouldn't be empty")
    String surname;

    @Valid
    GroupDto groupDto;

}
