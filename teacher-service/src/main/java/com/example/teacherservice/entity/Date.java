package com.example.teacherservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "date")
public class Date {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "day")
    private int day;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private List<Subject> subjects;







}
