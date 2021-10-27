package com.dddd.questionnaireportal.beans.managedBeans.webSocket;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;

@ServerEndpoint(value = "/responsesWS")
public class ResponseListEndpoint {

    static ArrayList<Session> sessions = new ArrayList<>();

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
    }

    public static ArrayList<Session> getSessions() {
        return sessions;
    }

}
