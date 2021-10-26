package com.dddd.questionnaireportal.beans.managedBeans.webSocket;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;

@ServerEndpoint(value = "/responsesWS")
public class ResponseListEndpoint {

    static ArrayList<Session> sessions = new ArrayList<>();

    @OnMessage
    public void messageReceiver(String message) {

        System.out.println("Received message:" + message);
    }

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("onOpen: " + session.getId());
        sessions.add(session);
        System.out.println("onOpen: Notification list size: " + sessions.size());
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("onClose: " + session.getId());
        sessions.remove(session);
    }

    public static ArrayList<Session> getSessions() {
        return sessions;
    }

}
