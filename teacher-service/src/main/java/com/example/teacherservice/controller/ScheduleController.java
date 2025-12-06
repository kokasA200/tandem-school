package com.example.teacherservice.controller;


import com.example.teacherservice.dto.ScheduleDto;
import com.example.teacherservice.dto.SubjectDto;
import com.example.teacherservice.entity.Schedule;
import com.example.teacherservice.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/scheduleApi")
@RequiredArgsConstructor
public class ScheduleController {


    private final ScheduleService scheduleService;


    @PostMapping("/addSchedule")
    public ResponseEntity<String> addSchedule(@RequestBody ScheduleDto scheduleDto) {
        scheduleService.addSchedule(scheduleDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/updateSchedule")
    public ResponseEntity<Schedule> updateSchedule(@RequestParam String day,
                                                   @RequestParam Set<SubjectDto> subjectsDto ) {
        return ResponseEntity.ok(scheduleService.updateSchedule(day, subjectsDto));
    }


    @GetMapping("/getByDay")
    public ResponseEntity<Schedule> getScheduleByDay(@RequestParam String day) {
        return ResponseEntity.ok(scheduleService.getSchedule(day));
    }


    @GetMapping("/getScheduleList")
    public ResponseEntity<List<Schedule>> getAllSchedules() {
        return ResponseEntity.ok(scheduleService.getAllSchedules());
    }


}
