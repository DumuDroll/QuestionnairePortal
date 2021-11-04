package com.dddd.questionnaireportal.common.util.emailUtil;


import com.dddd.questionnaireportal.common.contants.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class EmailUtil {

    private static final Logger logger = LogManager.getLogger();

    private static final String SENDER_USERNAME = "studyloaddocs@gmail.com";
    private static final String SENDER_PASSWORD = "kytttjlniejtdeeh";

    public static void sendEmail(String toAddress, String subject, String message) {

        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.auth", Constants.TRUE);
        properties.put("mail.debug", Constants.TRUE);
        properties.put("mail.smtp.ssl.checkserveridentity", Constants.TRUE);
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.socketFactory.port", "465");

        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER_USERNAME, SENDER_PASSWORD);
            }
        };

        Session session = Session.getInstance(properties, auth);
        try {
            // creates a new e-mail message
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(SENDER_USERNAME));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            msg.setText(message);

            // sends the e-mail
            Transport.send(msg);
        } catch (MessagingException mex) {
            logger.catching(mex);
        }
    }
}
