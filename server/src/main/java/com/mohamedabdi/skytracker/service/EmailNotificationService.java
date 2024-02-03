package com.mohamedabdi.skytracker.service;

import com.mohamedabdi.skytracker.dao.EmailNotificationDao;
import com.mohamedabdi.skytracker.model.EmailNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailNotificationService {

    private final EmailNotificationDao emailNotificationDao;

    @Autowired
    public EmailNotificationService(EmailNotificationDao emailNotificationDao) {
        this.emailNotificationDao = emailNotificationDao;
    }

    public void logEmailNotification(EmailNotification emailNotification) {
        emailNotificationDao.logEmailNotification(emailNotification);
    }

    public List<EmailNotification> getEmailNotificationsByUserId(Long userId) {
        return emailNotificationDao.getEmailNotificationsByUserId(userId);
    }
}
