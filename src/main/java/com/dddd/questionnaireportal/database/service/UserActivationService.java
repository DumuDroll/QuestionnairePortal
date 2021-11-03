package com.dddd.questionnaireportal.database.service;

import com.dddd.questionnaireportal.common.contants.Constants;
import com.dddd.questionnaireportal.common.util.MD5Util.MD5Util;
import com.dddd.questionnaireportal.common.util.date.DateHelper;
import com.dddd.questionnaireportal.common.util.emailUtil.EmailUtil;
import com.dddd.questionnaireportal.database.dao.SaverHelperDAO;
import com.dddd.questionnaireportal.database.dao.UserActivationDAO;
import com.dddd.questionnaireportal.database.entity.User;
import com.dddd.questionnaireportal.database.entity.UserActivation;

import java.util.Date;
import java.util.UUID;

public class UserActivationService {

    public static void update(UserActivation userActivation) {
        SaverHelperDAO.update(userActivation);
    }

    public static void updateForPassChange(User user, String newPass) {
        UserActivation userActivation = user.getUserActivation();
        userActivation.setPassChangeExpireDate(DateHelper.currentDatePlusOneDay());
        userActivation.setNewPass(MD5Util.getSecurePassword(newPass));
        EmailUtil.sendEmail(user.getEmail(), Constants.PASSWORD_CHANGE_SUBJECT,
                "Confirm password change on a link: http://localhost:8080/passChangeActivation?key="
                + userActivation.getUuid());
        update(userActivation);
    }

    public static boolean updateForPassChangeConfirmation(UserActivation userActivation) {
        Date date = new Date();
        if (date.compareTo(userActivation.getPassChangeExpireDate()) <= 0) {
            userActivation.setPassChangeExpireDate(date);
            userActivation.setUuid(UUID.randomUUID().toString());
            User user = userActivation.getUser();
            user.setPassword(userActivation.getNewPass());
            UserService.updateUser(user);
            UserActivationService.update(userActivation);
            return true;
        }
        return false;
    }

    public static void updateForForgotPassLetter(User user) {
        UserActivation userActivation = user.getUserActivation();
        userActivation.setForgotPassExpireDate(DateHelper.currentDatePlusOneDay());
        SaverHelperDAO.update(userActivation);
        EmailUtil.sendEmail(user.getEmail(), "Questionnaire Portal: new password",
                "http://localhost:8080/newPassConfirmation?key=" + userActivation.getUuid());
    }

    public static boolean updateForNewPassConfirmation(UserActivation userActivation) {
        Date date = new Date();
        if (date.compareTo(userActivation.getForgotPassExpireDate()) <= 0) {
            userActivation.setForgotPassExpireDate(date);
            SaverHelperDAO.update(userActivation);
            return true;
        }
        return false;
    }
    public static UserActivation findByUUID(String uuid) {
        return UserActivationDAO.findByUUID(uuid);
    }
}
