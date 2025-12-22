package com.example.teacherservice.docs.student;


import com.example.teacherservice.dto.StudentDto;
import com.example.teacherservice.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Operation(
        summary = "Добавить нового студента",
        description = "Принимает данные студента, валидирует их и сохраняет в базу данных.",
        requestBody = @RequestBody(
                description = "Данные создаваемого студента",
                required = true,
                content = @Content(
                        schema = @Schema(implementation = StudentDto.class),
                        mediaType = MediaType.APPLICATION_JSON_VALUE
                )
        ),
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Студент успешно добавлен",
                        content = @Content(
                                schema = @Schema(type = "string"),
                                mediaType = MediaType.TEXT_PLAIN_VALUE,
                                examples = @ExampleObject(value = "Student added successfully!")
                        )
                ),
                @ApiResponse(
                        responseCode = "400",
                        description = "Ошибка валидации входных данных",
                        content = @Content(
                                schema = @Schema(implementation = ErrorResponse.class),
                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                examples = @ExampleObject(
                                        value = """
                                                {
                                                    "exceptionClass": "MethodArgumentNotValidException",
                                                    "message": "name shouldn't be empty",
                                                    "timestamp": "2025-05-10T12:00:00"
                                                }
                                                """
                                )
                        )
                )
        }
)

public @interface AddStudentDoc {
}
