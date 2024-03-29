package com.dddd.questionnaireportal.database.service;

import com.dddd.questionnaireportal.common.util.wsmessageUtil.MessageSenderUtil;
import com.dddd.questionnaireportal.database.dao.SaverHelperDAO;
import com.dddd.questionnaireportal.database.dao.ResponseDAO;
import com.dddd.questionnaireportal.database.entity.Response;
import com.google.gson.Gson;

import java.util.*;

public class ResponseService {

    public static List<Response> findAll() {
        return ResponseDAO.findAll();
    }

    public static void saveResponsesAndSendThemViaWebsocket(List<Response> responses) {
        Map<String, String> responseMap = new HashMap<>();
        UUID responsePerUser = UUID.randomUUID();
        responses.forEach(response -> {
            response.setResponsePerUser(responsePerUser);
            SaverHelperDAO.save(response);
            responseMap.put(response.getLabel(), response.getResponse());
            response.setResponse(null);
        });
        Gson gson = new Gson();
        String json = gson.toJson(responseMap);
        MessageSenderUtil.sendMessage(json);
    }

}
