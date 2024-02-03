package com.mohamedabdi.skytracker.dao;

import com.mohamedabdi.skytracker.model.User;

import java.util.List;

public interface UserDao {
    int addUser(User user);
    User getUserByEmail(String email);
    List<User> getAllUsers();
    int updateUser(int userId, User user);
    int deleteUser(int userId);
}
