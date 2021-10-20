package com.dddd.questionnaireportal.database.service.impl;


import com.dddd.questionnaireportal.database.dao.impl.UserDAOImpl;
import com.dddd.questionnaireportal.database.entity.User;
import com.dddd.questionnaireportal.database.service.UserService;

public class UserServiceImpl implements UserService {

    private final UserDAOImpl userDAO = new UserDAOImpl();

    public User findByEmail(String email) {

        return userDAO.findByEmail(email);
    }

    public void createUser(User user) {
        userDAO.save(user);
    }

    public void updateUser(User user) {
        userDAO.update(user);
    }

    public User findUser(int userId) {
        return userDAO.findById(userId);
    }
}