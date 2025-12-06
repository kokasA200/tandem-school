package com.example.teacherservice.dto;

import com.example.teacherservice.entity.enumDay.Days;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
public class ScheduleDto {


    Days days;

    @Valid
    Set<SubjectDto> subjects;


}
