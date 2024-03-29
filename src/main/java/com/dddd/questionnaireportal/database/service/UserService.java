package com.dddd.questionnaireportal.database.service;

import com.dddd.questionnaireportal.common.contants.Constants;
import com.dddd.questionnaireportal.common.util.MD5Util.MD5Util;
import com.dddd.questionnaireportal.common.util.dateJava.DateHelper;
import com.dddd.questionnaireportal.common.util.emailUtil.EmailUtil;
import com.dddd.questionnaireportal.database.dao.SaverHelperDAO;
import com.dddd.questionnaireportal.database.dao.UserDAO;
import com.dddd.questionnaireportal.database.entity.User;
import com.dddd.questionnaireportal.database.entity.UserActivation;

import java.util.UUID;

public class UserService {
    public static User findByEmail(String email) {
        return UserDAO.findByEmail(email);
    }

    public static void createUser(User user) {
        UserActivation userActivation = new UserActivation();
        UUID uuid = UUID.randomUUID();
        userActivation.setUuid(uuid.toString());
        SaverHelperDAO.save(user);
        userActivation.setUser(user);
        userActivation.setConfirmationExpireDate(DateHelper.currentDatePlusOneDay());
        SaverHelperDAO.save(userActivation);
        EmailUtil.sendEmail(user.getEmail(), Constants.USER_REGISTRATION_SUBJECT,
                "Activate account on a link:" + Constants.DOMAIN_ADDRESS + "registrationActivation?key=" + uuid);
    }

    public static void updateActivationLink(User user) {
        UserActivation userActivation = user.getUserActivation();
        userActivation.setConfirmationExpireDate(DateHelper.currentDatePlusOneDay());
        SaverHelperDAO.update(userActivation);
        EmailUtil.sendEmail(user.getEmail(), Constants.USER_REGISTRATION_SUBJECT,
                "Activate account on a link:" + Constants.DOMAIN_ADDRESS + "registrationActivation?key=" + userActivation.getUuid());
    }

    public static void updateUser(User user) {
        SaverHelperDAO.update(user);
    }

    public static void updateUserForPassReset(User user, String newPass) {
        user.setPassword(MD5Util.getSecurePassword(newPass));
        UserActivation userActivation = user.getUserActivation();
        userActivation.setUuid(UUID.randomUUID().toString());
        SaverHelperDAO.update(userActivation);
        SaverHelperDAO.update(user);
    }
}
