package com.example.teacherservice.docs.student;

import com.example.teacherservice.dto.StudentDto;
import com.example.teacherservice.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Operation(
        summary = "Найти студента по имени и фамилии",
        description = "Возвращает полные данные студента на основе его имени и фамилии.",
        parameters = {
                @Parameter(
                        name = "name",
                        description = "Имя студента",
                        example = "Иван",
                        required = true
                ),
                @Parameter(
                        name = "surname",
                        description = "Фамилия студента",
                        example = "Иванов",
                        required = true
                )
        },
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Студент успешно найден",
                        content = @Content(
                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                schema = @Schema(implementation = StudentDto.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "404",
                        description = "Студент с такими данными не найден",
                        content = @Content(
                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                schema = @Schema(implementation = ErrorResponse.class),
                                examples = @ExampleObject(
                                        value = """
                                                {
                                                    "error": "Not Found",
                                                    "message": "Student not found with name: Иван, surname: Иванов",
                                                    "status": 404
                                                }
                                                """
                                )
                        )
                ),
                @ApiResponse(
                        responseCode = "400",
                        description = "Ошибка в параметрах запроса",
                        content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                )
        }
)
public @interface GetStudentDoc {
}