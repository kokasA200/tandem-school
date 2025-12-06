package com.example.teacherservice.service;


import com.example.teacherservice.dto.ScheduleDto;
import com.example.teacherservice.dto.SubjectDto;
import com.example.teacherservice.entity.Schedule;
import com.example.teacherservice.entity.Subject;
import com.example.teacherservice.entity.enumDay.Days;
import com.example.teacherservice.exception.ScheduleAlreadyExistException;
import com.example.teacherservice.exception.ScheduleNotFoundException;
import com.example.teacherservice.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public Schedule addSchedule(ScheduleDto scheduleDto) {

        scheduleRepository.findByDay(scheduleDto.getDays()).ifPresent(existingSchedule -> {
            throw new ScheduleAlreadyExistException("Расписание на день '" + scheduleDto.getDays() + "' уже существует.");
        });

        Schedule schedule = new Schedule();

        schedule.setDay(scheduleDto.getDays());
        Set<SubjectDto> subjectsDto = scheduleDto.getSubjects();

        Set<Subject> subjects = subjectsDto.stream()
                .map(subjectDto -> {
                    Subject subject = new Subject();
                    subject.setName(subjectDto.getName());
                    return subject;
                })
                .collect(Collectors.toSet());
        schedule.setSubjects(subjects);
        scheduleRepository.save(schedule);
        return schedule;
    }



    public Schedule updateSchedule(String day, Set<SubjectDto> subjectsDto) {

        Schedule schedule = scheduleRepository.findByDay(Days.valueOf(day))
                .orElseThrow(() -> new ScheduleNotFoundException("Schedule not found name '" + day + "'."));

       Set<Subject> subjects = subjectsDto.stream()
                .map(subjectDto -> {
                    Subject subject = new Subject();
                    subject.setName(subjectDto.getName());
                    return subject;
                }).collect(Collectors.toSet());

        schedule.setSubjects(subjects);

        return scheduleRepository.save(schedule);
    }



    public Schedule getSchedule(String day) {
       Schedule schedule = scheduleRepository.findByDay(Days.valueOf(day))
               .orElseThrow(() -> new ScheduleNotFoundException("Schedule not found name '" + day + "'."));
       return schedule;

    }






    public List<Schedule> getAllSchedules(){
        return scheduleRepository.findAll();
    }






}





