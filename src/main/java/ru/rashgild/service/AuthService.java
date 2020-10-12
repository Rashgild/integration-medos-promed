package ru.rashgild.service;


import ru.rashgild.model.Token;

import java.util.Map;

public interface AuthService {

    Token getToken();

    default String getSessionId() {
        return getToken() != null ? getToken().getSessId() : "";
    }

    boolean logout();

    Map.Entry<String, String> getHeader();
}
