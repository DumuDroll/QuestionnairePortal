package com.dddd.questionnaireportal.beans.managedBeans.webSocket;

import javax.websocket.Session;
import java.util.List;

public class MessageSender {

    public static void sendMessage(String message) {
        List<Session> list = ResponseListEndpoint.getSessions();
        System.out.println("Notification list size: " + list.size());
        for (Session s : list) {
            if (s.isOpen()) {
                System.out.println("Sending Notification To: " + s.getId());
                s.getAsyncRemote().sendText(message);
            }
        }
    }
}
