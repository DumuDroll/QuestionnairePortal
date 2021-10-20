package com.dddd.questionnaireportal.database.service.impl;


import com.dddd.questionnaireportal.database.entity.User;
import com.dddd.questionnaireportal.database.service.UserService;

import com.dddd.questionnaireportal.database.dao.UserDAO;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean(name = "userService")
public class UserServiceImpl implements UserService {

    @ManagedProperty("#{userDAO}")
    private UserDAO userDAO;

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean findByEmail(String email){
        userDAO.beginTransaction();
        User user = userDAO.findUserByEmail(email);
        userDAO.closeTransaction();
        return user!=null;
    }

    public boolean isValidLogin(String email, String password){
        userDAO.beginTransaction();
        User user = userDAO.findUserByEmail(email);
        userDAO.closeTransaction();
        return user != null && user.getPassword().equals(password);
    }

    public void createUser(User user){
        userDAO.beginTransaction();
        userDAO.save(user);
        userDAO.commitAndCloseTransaction();
    }

    public void updateUser(User user){
        userDAO.beginTransaction();
        User persistedUser = userDAO.find(user.getId());
        persistedUser.setActive(user.isActive());
        persistedUser.setEmail(user.getEmail());
        persistedUser.setFirstName(user.getFirstName());
        persistedUser.setLastName(user.getLastName());
        persistedUser.setPhoneNumber(user.getPhoneNumber());
        persistedUser.setPassword(user.getPassword());
        userDAO.update(persistedUser);
        userDAO.commitAndCloseTransaction();
    }

    public User findUser(int userId){
        userDAO.beginTransaction();
        User user = userDAO.find(userId);
        userDAO.closeTransaction();
        return user;
    }
}