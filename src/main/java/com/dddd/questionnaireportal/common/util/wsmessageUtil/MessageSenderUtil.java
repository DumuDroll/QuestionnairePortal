package com.dddd.questionnaireportal.common.util.wsmessageUtil;

import com.dddd.questionnaireportal.beans.managedBeans.webSocket.ResponseListEndpoint;

import javax.websocket.Session;
import java.util.List;

public class MessageSenderUtil {

    public static void sendMessage(String message) {
        List<Session> list = ResponseListEndpoint.getSessions();
        System.out.println("Open sessions: " + list.size());
        for (Session s : list) {
            if (s.isOpen()) {
                s.getAsyncRemote().sendText(message);
            }
        }
    }
}
