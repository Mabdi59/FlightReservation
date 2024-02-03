package com.mohamedabdi.skytracker.model;

import java.time.LocalDateTime;

public class EmailNotification {
    private Long notificationId;
    private Long userId;
    private String emailSubject;
    private String emailBody;
    private LocalDateTime sentDate;

    // Constructors, Getters, and Setters
    public EmailNotification() {}

    public EmailNotification(Long notificationId, Long userId, String emailSubject, String emailBody, LocalDateTime sentDate) {
        this.notificationId = notificationId;
        this.userId = userId;
        this.emailSubject = emailSubject;
        this.emailBody = emailBody;
        this.sentDate = sentDate;
    }

    // Getters and setters

    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    public String getEmailBody() {
        return emailBody;
    }

    public void setEmailBody(String emailBody) {
        this.emailBody = emailBody;
    }

    public LocalDateTime getSentDate() {
        return sentDate;
    }

    public void setSentDate(LocalDateTime sentDate) {
        this.sentDate = sentDate;
    }
}
