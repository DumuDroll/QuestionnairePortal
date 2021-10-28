package com.dddd.questionnaireportal.database.service;

import com.dddd.questionnaireportal.common.contants.Constants;
import com.dddd.questionnaireportal.common.util.MD5Util.MD5Util;
import com.dddd.questionnaireportal.common.util.date.DateHelper;
import com.dddd.questionnaireportal.common.util.emailUtil.EmailUtil;
import com.dddd.questionnaireportal.database.dao.SaverHelperDAO;
import com.dddd.questionnaireportal.database.dao.UserActivationDAO;
import com.dddd.questionnaireportal.database.entity.User;
import com.dddd.questionnaireportal.database.entity.UserActivation;

import javax.mail.MessagingException;

public class UserActivationService {

    public static void update(UserActivation userActivation){
        SaverHelperDAO.update(userActivation);
    }

    public static void updateForPassChange(User user, String newPass) throws MessagingException {
        UserActivation userActivation = user.getUserActivation();
        userActivation.setPassChangeExpireDate(DateHelper.cuurentDatePlusOneDay());
        userActivation.setNewPass(MD5Util.getSecurePassword(newPass, user.getSalt()));
        EmailUtil.sendEmail(user.getEmail(), Constants.PASSWORD_CHANGE_SUBJECT, Constants.PASSWORD_CHANGE_LINK+userActivation.getUuid());
        update(userActivation);
    }

    public static UserActivation findByUUID(String uuid){
        return UserActivationDAO.findByUUID(uuid);
    }
}
