package com.mohamedabdi.skytracker.dao;

import com.mohamedabdi.skytracker.model.EmailNotification;
import java.util.List;

public interface EmailNotificationDao {
    void logEmailNotification(EmailNotification emailNotification);
    List<EmailNotification> getEmailNotificationsByUserId(Long userId);
}
