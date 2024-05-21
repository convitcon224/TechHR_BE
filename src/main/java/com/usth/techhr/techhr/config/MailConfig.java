package com.usth.techhr.techhr.config;

import com.usth.techhr.techhr.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableScheduling
public class MailConfig {
    @Autowired
    private EmailSenderService senderService;

//    @Scheduled(fixedRate = 5, timeUnit = TimeUnit.MINUTES)
//    public void sendMail() {
//        String[] emails = {"dungbu2000@gmail.com","dungbu20000@gmail.com"};
//        senderService.sendEmail(emails,
//                "Test email every 5m",
//                "A test email content");
//    }

//    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.SECONDS)
//    public void testSchedule() {
//        System.out.println("Task 1 " + Thread.currentThread().getName());
//    }
}
