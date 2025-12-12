package com.example.teacherservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableFeignClients
public class TeacherServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeacherServiceApplication.class, args);
    }

}
