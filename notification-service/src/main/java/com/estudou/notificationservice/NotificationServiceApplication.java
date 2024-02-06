package com.estudou.notificationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

import com.estudou.notificationservice.event.ScheduleVinculateGoalEvent;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class NotificationServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }

    @KafkaListener(topics = "notificationTopic")
    public void handleNotification(ScheduleVinculateGoalEvent scheduleVinculateGoalEvent) {
        // send out a email notification
        log.info("notification for goal - {}", scheduleVinculateGoalEvent.getGoalId());
    }
}
