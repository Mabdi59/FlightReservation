package com.mohamedabdi.skytracker.dao;

import com.mohamedabdi.skytracker.dao.UserDao;
import com.mohamedabdi.skytracker.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcUserDao implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<User> userRowMapper = (rs, rowNum) -> {
        User user = new User();
        user.setUserId(rs.getInt("user_id"));
        user.setEmail(rs.getString("email"));
        return user;
    };

    @Override
    public int addUser(User user) {
        String sql = "INSERT INTO users (email) VALUES (?)";
        return jdbcTemplate.update(sql, user.getEmail());
    }

    @Override
    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{email}, userRowMapper);
    }

    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, userRowMapper);
    }

    @Override
    public int updateUser(int userId, User user) {
        String sql = "UPDATE users SET email = ? WHERE user_id = ?";
        return jdbcTemplate.update(sql, user.getEmail(), userId);
    }

    @Override
    public int deleteUser(int userId) {
        String sql = "DELETE FROM users WHERE user_id = ?";
        return jdbcTemplate.update(sql, userId);
    }
}
