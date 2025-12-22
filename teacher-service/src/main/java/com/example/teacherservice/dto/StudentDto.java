package com.example.teacherservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {

    @Schema(description = "student name", examples = "Иван")
    @NotBlank(message = "name shouldn't be empty")
    String name;

    @Schema(description = "student surname", examples = "Иванов")
    @NotBlank(message = "surname shouldn't be empty")
    String surname;

    @Valid
    @Schema(description = "information about group")
    GroupDto groupDto;

}
