package com.mohamedabdi.skytracker.dao;

import com.mohamedabdi.skytracker.model.User;
import java.util.List;

public interface UserDao {
    void addUser(User user);
    User getUserById(int userId);
    List<User> getAllUsers();
    void updateUser(User user);
    void deleteUser(int userId);
}
