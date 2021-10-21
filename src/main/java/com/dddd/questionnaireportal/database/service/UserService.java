package com.dddd.questionnaireportal.database.service;

import com.dddd.questionnaireportal.database.dao.UserDAO;
import com.dddd.questionnaireportal.database.entity.User;

public class UserService {
    public static User findByEmail(String email) {
        return UserDAO.findByEmail(email);
    }

    public static void createUser(User user) {
        UserDAO.save(user);
    }

    public static void updateUser(User user) {
        UserDAO.update(user);
    }

    public static User findUser(int userId) {
        return UserDAO.findById(userId);
    }
}
