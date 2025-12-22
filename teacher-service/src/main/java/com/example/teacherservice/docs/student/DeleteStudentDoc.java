package com.example.teacherservice.docs.student;


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
        summary = "Удалить студента",
        description = "Удаляет студента по имени и фамилии",
        parameters = {
                @Parameter(
                        name = "name",
                        description = "имя студента",
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
                        description = "Студент успешно удален",
                        content = @Content(
                                mediaType = MediaType.TEXT_PLAIN_VALUE,
                                examples = @ExampleObject(value = "Студент успешно удален")
                        )
                ),


                @ApiResponse(
                        responseCode = "400",
                        description = "Студент не найдет",
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
public @interface DeleteStudentDoc {
}
