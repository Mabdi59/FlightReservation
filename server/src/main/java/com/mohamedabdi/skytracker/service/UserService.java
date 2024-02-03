package com.mohamedabdi.skytracker.service;

import com.mohamedabdi.skytracker.dao.UserDao;
import com.mohamedabdi.skytracker.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public int addUser(User user) {
        return userDao.addUser(user);
    }

    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public int updateUser(int userId, User user) {
        return userDao.updateUser(userId, user);
    }

    public int deleteUser(int userId) {
        return userDao.deleteUser(userId);
    }
}
