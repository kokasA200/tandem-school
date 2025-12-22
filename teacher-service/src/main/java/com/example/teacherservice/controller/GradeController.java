package com.example.teacherservice.controller;


import com.example.teacherservice.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gradeApi")
@RequiredArgsConstructor
public class GradeController {

    private final GradeService gradeService;



    @PostMapping("/addGrade")
    public ResponseEntity<Void> addGrade(@RequestParam String name, @RequestParam String surname,
                                         @RequestParam String subjectName, @RequestParam String weekday,
                                         @RequestParam int day, @RequestParam int gradeDto) {

        gradeService.giveGrade(name, surname, subjectName, weekday, day, gradeDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/updateGrade")
    public ResponseEntity<Void> updateGrade(@RequestParam Long gradeId, @RequestParam int valueGrade) {
        gradeService.updateGrade(gradeId, valueGrade);
        return ResponseEntity.ok().build();
    }


}
