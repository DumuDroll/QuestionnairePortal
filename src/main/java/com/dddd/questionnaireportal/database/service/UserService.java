package com.dddd.questionnaireportal.database.service;

import com.dddd.questionnaireportal.common.contants.Constants;
import com.dddd.questionnaireportal.common.util.emailUtil.EmailUtil;
import com.dddd.questionnaireportal.database.dao.SaverHelperDAO;
import com.dddd.questionnaireportal.database.dao.UserDAO;
import com.dddd.questionnaireportal.database.entity.User;
import com.dddd.questionnaireportal.database.entity.UserActivation;

import javax.mail.MessagingException;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class UserService {
    public static User findByEmail(String email) {
        return UserDAO.findByEmail(email);
    }

    public static void createUser(User user) throws MessagingException {
        UserActivation userActivation = new UserActivation();
        UUID uuid = UUID.randomUUID();
        userActivation.setUuid(uuid);
        userActivation.setUser(user);
        Date date = new Date();
        Date dayAfter = new Date(date.getTime() + TimeUnit.DAYS.toMillis( 1 ));
        userActivation.setConfirmationExpireDate(dayAfter);
        SaverHelperDAO.save(userActivation);
        EmailUtil.sendEmail(user.getEmail(), Constants.USER_REGISTRATION_SUBJECT,
                Constants.USER_ACTIVATION_LINK + uuid);
        SaverHelperDAO.save(user);
    }

    public static void updateUser(User user) {
        UserDAO.update(user);
    }

    public static User findUser(int userId) {
        return UserDAO.findById(userId);
    }
}
