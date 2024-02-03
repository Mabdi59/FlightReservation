package com.mohamedabdi.skytracker.controller;

import com.mohamedabdi.skytracker.model.EmailNotification;
import com.mohamedabdi.skytracker.service.EmailNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class EmailNotificationController {

    private final EmailNotificationService emailNotificationService;

    @Autowired
    public EmailNotificationController(EmailNotificationService emailNotificationService) {
        this.emailNotificationService = emailNotificationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void logEmailNotification(@RequestBody EmailNotification emailNotification) {
        emailNotificationService.logEmailNotification(emailNotification);
    }

    @GetMapping("/{userId}")
    public List<EmailNotification> getEmailNotificationsByUserId(@PathVariable Long userId) {
        return emailNotificationService.getEmailNotificationsByUserId(userId);
    }
}
