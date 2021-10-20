package com.dddd.questionnaireportal.database.service;


import com.dddd.questionnaireportal.database.entity.User;

public interface UserService {
    void createUser(User user);
    void updateUser(User user);
    User findUser(int userId);
}
