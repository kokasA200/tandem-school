package com.example.teacherservice.kafkaConfig;


import com.example.teacherservice.dto.SubjectGradeDto;
import com.example.teacherservice.service.GradeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;


@Slf4j
@Service
@RequiredArgsConstructor
@EnableKafka
public class KafkaProducerService {

    @Value("${kafka.topic.name}")
    private String topicName;

    private final GradeService gradeService;
    private final KafkaTemplate<String, SubjectGradeDto> kafkaTemplate;

    public List<CompletableFuture<SendResult<String, SubjectGradeDto>>> sendTodayGrades(int day) {
        log.info("Attempting to send all students' grades for day: {}", day);

        Map<String, List<SubjectGradeDto>> allStudentsGrades = gradeService.getAllStudentsGradesForDay(day);

        List<CompletableFuture<SendResult<String, SubjectGradeDto>>> futures = new ArrayList<>();

        if (allStudentsGrades.isEmpty()) {
            log.info("No grades found for day: {}", day);
            return futures;
        }

        allStudentsGrades.forEach((studentFullName, gradesForStudent) -> {
            gradesForStudent.forEach(gradeDto -> {
                String key = UUID.randomUUID().toString();

                CompletableFuture<SendResult<String, SubjectGradeDto>> future = kafkaTemplate.send(topicName, key, gradeDto);

                future.whenComplete((result, ex) -> {
                    if (ex == null) {
                        log.info("Successfully sent grade: [{}] for student: [{}] to topic: [{}], partition: [{}], offset: [{}]",
                                gradeDto, studentFullName, result.getRecordMetadata().topic(),
                                result.getRecordMetadata().partition(), result.getRecordMetadata().offset());
                    } else {
                        log.error("Failed to send grade: [{}] for student: [{}] with key: [{}] to topic: [{}]. Reason: {}",
                                gradeDto, studentFullName, key, topicName, ex.getMessage(), ex); // Используем log.error и передаем ex
                    }
                });
                futures.add(future);
            });
        });

        return futures;
    }
}





