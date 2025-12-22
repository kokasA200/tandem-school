package com.example.teacherservice.kafkaConfig;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class NotificationToKafkaTopic {

    public final KafkaProducerService kafkaProducerService;

    int day = 1;

    @Scheduled(cron = "0 0 14 ? * MON-FRI", zone = "Europe/Moscow")
    public void sendToTopic(){
        log.info("send grades to kafka topic");
        kafkaProducerService.sendTodayGrades(day);
        day = day + 1;
    }


}
