package com.mohamedabdi.skytracker.dao;

import com.mohamedabdi.skytracker.dao.EmailNotificationDao;
import com.mohamedabdi.skytracker.model.EmailNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcEmailNotificationDao implements EmailNotificationDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcEmailNotificationDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void logEmailNotification(EmailNotification emailNotification) {
        String sql = "INSERT INTO email_notifications (user_id, email_subject, email_body, sent_date) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, emailNotification.getUserId(), emailNotification.getEmailSubject(), emailNotification.getEmailBody(), emailNotification.getSentDate());
    }

    @Override
    public List<EmailNotification> getEmailNotificationsByUserId(Long userId) {
        String sql = "SELECT * FROM email_notifications WHERE user_id = ?";
        return jdbcTemplate.query(sql, new Object[]{userId}, (rs, rowNum) -> new EmailNotification(
                rs.getLong("notification_id"),
                rs.getLong("user_id"),
                rs.getString("email_subject"),
                rs.getString("email_body"),
                rs.getTimestamp("sent_date").toLocalDateTime()
        ));
    }
}
