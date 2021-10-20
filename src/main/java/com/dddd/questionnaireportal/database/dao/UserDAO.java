package com.dddd.questionnaireportal.database.dao;

import com.dddd.questionnaireportal.database.entity.User;

public interface UserDAO extends DAO<User>{
    User findByEmail(String email);

    User findById(int id);
}
